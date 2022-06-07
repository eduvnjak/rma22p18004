package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorRequestBody
import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object OdgovorRepository {
    suspend fun getOdgovoriAnketa(idAnkete: Int): List<Odgovor>{
        return withContext(Dispatchers.IO) {
            val odgovoriZaAnketu = mutableListOf<Odgovor>()
            var pokusaj = TakeAnketaRepository.dajPokusajZaAnketu(idAnkete)
            if (pokusaj == null) {
                return@withContext odgovoriZaAnketu
            } else{
                try{
                    val odgovoriZaPokusaj = ApiAdapter.retrofit.dajOdgovoreZaPokusaj(AccountRepository.getHash(), pokusaj.id).body()
                    return@withContext odgovoriZaPokusaj ?: mutableListOf()
                } catch (e: IllegalStateException){
                    return@withContext mutableListOf<Odgovor>()
                }
            }
        }
    }

    suspend fun postaviOdgovorAnketa(idAnketaTaken: Int, idPitanje: Int, odgovor: Int): Int{
        return withContext(Dispatchers.IO) {
            try{
                val anketaTaken = ApiAdapter.retrofit.dajSvePokusaje(AccountRepository.getHash()).body()!!.find { anketaTaken -> anketaTaken.id == idAnketaTaken }
                val pitanjaNaAnketi = PitanjeAnketaRepository.getPitanja(anketaTaken!!.AnketumId)
                val pitanje = pitanjaNaAnketi.find { pitanje -> pitanje.id == idPitanje }
                val progres = zaokruzenProgres(getOdgovoriAnketa(anketaTaken.AnketumId).size + 1, pitanjaNaAnketi.size)
                val response = ApiAdapter.retrofit.postaviOdgovorZaPokusaj(
                    AccountRepository.getHash(),
                    anketaTaken.id,
                    OdgovorRequestBody(odgovor, idPitanje, progres))
                return@withContext progres
            } catch (e: Exception) {
                Log.i("test", "greska")
                return@withContext -1
            }
        }
    }
    private fun zaokruzenProgres (odgovoreno: Int, ukupno: Int): Int{
        if(ukupno != 0) {
            val omjer = odgovoreno.toFloat()/ukupno.toFloat()
            if(omjer >= 0 && omjer<0.1){
                return 0
            }else if (omjer >= 0.1 && omjer < 0.3){
                return 20
            }else if (omjer >= 0.3 && omjer < 0.5){
                return 40
            }else if (omjer >= 0.5 && omjer < 0.7){
                return 60
            }else if (omjer >= 0.7 && omjer < 0.9){
                return 80
            }else return 100
        }else {
            return 0
        }
    }
}