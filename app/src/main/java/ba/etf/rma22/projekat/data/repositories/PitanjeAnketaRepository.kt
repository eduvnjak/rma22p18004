package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.dao.PitanjeDao
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

@SuppressLint("StaticFieldLeak")
object PitanjeAnketaRepository {
    private lateinit var context: Context
    fun setContext(_context: Context) {
        context = _context
    }

    suspend fun getPitanja(idAnkete: Int?): List<Pitanje> {
        return withContext(Dispatchers.IO) {
            if(idAnkete != null) {
                try {
                    val response = ApiAdapter.retrofit.dajPitanjaZaAnketu(idAnkete)
                    upisiPitanjaUBazu(response.body(), idAnkete)
                    return@withContext response.body()!!
                } catch (exception: Exception) {
                    return@withContext emptyList<Pitanje>()
                }
            } else{
                return@withContext emptyList<Pitanje>()
            }
        }
    }
    //trebal mi ovdje ispod return with context
    private suspend fun upisiPitanjaUBazu(pitanja: List<Pitanje>?, idAnkete: Int) {
        if(pitanja != null){
            pitanja.forEach { pitanje -> pitanje.anketaId = idAnkete }
            val db = AppDatabase.getInstance(context)
            db.pitanjeDao().insertPitanje(*pitanja.toTypedArray())
        }
    }
    suspend fun getPitanjaBaza(idAnkete: Int): List<Pitanje> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            return@withContext db.pitanjeDao().getPitanjaZaAnketu(idAnkete)
        }
    }
}