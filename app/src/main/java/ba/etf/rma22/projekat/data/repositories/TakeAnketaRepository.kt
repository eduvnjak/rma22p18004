package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.ApiAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {

    suspend fun zapocniAnketu(idAnkete: Int): AnketaTaken? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiAdapter.retrofit.zapocniAnketu(AccountRepository.getHash(), idAnkete)
                val responseBody = response.body()
                return@withContext responseBody
            }catch (e: Exception) {
                println("Greska sa servisom")
                return@withContext null
            }
        }
    }
    suspend fun getPoceteAnkete(): List<AnketaTaken> {
        return withContext(Dispatchers.IO) {
            try{
                val response = ApiAdapter.retrofit.dajSvePokusaje(AccountRepository.getHash())
                val responseBody = response.body()
                if (responseBody!!.isEmpty()) return@withContext emptyList()
                return@withContext responseBody
            }
            catch(e: Exception) {
                println("Greska pri pozivu servisa")
                return@withContext emptyList()
            }
        }
    }
    suspend fun dajPokusajZaAnketu(anketaId: Int): AnketaTaken? {
        return withContext(Dispatchers.IO) {
            val poceteAnkete = getPoceteAnkete().sortedByDescending { anketaTaken -> anketaTaken.id }
            val zapocetiPokusaj = poceteAnkete.find { at -> at.AnketumId == anketaId }
            return@withContext zapocetiPokusaj
        }
    }
}