package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Odgovor
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

    fun postaviOdgovorAnketa(idAnketaTaken: Int,idPitanje: Int,odgovor: Int): Int{
        return 1
    }
}