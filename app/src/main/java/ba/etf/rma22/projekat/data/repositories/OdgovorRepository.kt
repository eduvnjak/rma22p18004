package ba.etf.rma22.projekat.data.repositories

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
                val odgovoriZaPokusaj = ApiAdapter.retrofit.dajOdgovoreZaPokusaj(AccountRepository.hashCode(), pokusaj.id).body()
                return@withContext odgovoriZaPokusaj!!
            }
        }
    }
    fun postaviOdgovorAnketa(idAnketaTaken: Int,idPitanje: Int,odgovor: Int): Int{
        return 1
    }
}