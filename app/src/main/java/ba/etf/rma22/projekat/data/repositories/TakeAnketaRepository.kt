package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.ApiAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object TakeAnketaRepository {
    private lateinit var context: Context
    fun setContext(_context: Context) {
        context = _context
    }
    suspend fun zapocniAnketu(idAnkete: Int): AnketaTaken? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiAdapter.retrofit.zapocniAnketu(AccountRepository.getHash(), idAnkete)
                val responseBody = response.body()
                //todo upisi ovaj novi pokusaj u bazu
                return@withContext responseBody
            }catch (e: Exception) {
                println("Greska sa servisom")
                return@withContext null
            }
        }
    }
    suspend fun getPoceteAnkete(): List<AnketaTaken>? {
        return withContext(Dispatchers.IO) {
            try{
                val response = ApiAdapter.retrofit.dajSvePokusaje(AccountRepository.getHash())
                val responseBody = response.body()
                if (responseBody!!.isEmpty()) return@withContext null
                upisiPoceteAnketeUBazu(responseBody)
                return@withContext responseBody
            }
            catch(e: Exception) {
                println("Greska pri pozivu servisa")
                return@withContext null
            }
        }
    }

    private suspend fun upisiPoceteAnketeUBazu(pokusaji: List<AnketaTaken>) {
        val db = AppDatabase.getInstance(context)
        db.anketaTakenDao().insertAnketaTaken(*pokusaji.toTypedArray())
    }
    suspend fun getPoceteAnketeBaza(): List<AnketaTaken>? {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            return@withContext db.anketaTakenDao().getAll()
        }
    }
    suspend fun dajPokusajZaAnketu(anketaId: Int): AnketaTaken? {
        return withContext(Dispatchers.IO) {
            try{
                val poceteAnkete = getPoceteAnkete()!!.sortedByDescending { anketaTaken -> anketaTaken.id }
                val zapocetiPokusaj = poceteAnkete.find { at -> at.AnketumId == anketaId }
                return@withContext zapocetiPokusaj
            } catch (npe: NullPointerException) {
                return@withContext null
            }
        }
    }
    suspend fun dajPokusajZaAnketuBaza(anketaId: Int): AnketaTaken?{
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(context)
            return@withContext db.anketaTakenDao().getAnketaTaken(anketaId)
        }
    }
}