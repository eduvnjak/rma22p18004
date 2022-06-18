package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("StaticFieldLeak")
object AccountRepository {
    var acHash = "725a10c7-9c21-412d-a972-4c0468fbd599"

    private lateinit var context: Context
    fun setContext(_context: Context) {
        context = _context
    }

    suspend fun postaviHash(acHash: String): Boolean {
        return withContext(Dispatchers.IO){
            if(acHash != getHash()){
                this@AccountRepository.acHash = acHash
                obnoviBazu(acHash)
                //return true
            }//treba li obnovu svakako uraditi?
            return@withContext true
            //return@withContext false
        }
    }

    private suspend fun obnoviBazu(acHash: String) {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val db = AppDatabase.getInstance(context)
                val accountDao = db.accountDao()
                db.anketaDao().deleteAll()
                db.anketaGrupaDao().deleteAll()
                db.anketaTakenDao().deleteAll()
                db.grupaDao().deleteAll()
                db.istrazivanjeDao().deleteAll()
                db.odgovorDao().deleteAll()
                db.pitanjeDao().deleteAll()
                accountDao.updateHash(acHash)
            } catch(e: Exception) {

            }
        }
    }

    suspend fun getHash(): String {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            val accDao = db.accountDao()
            val accounts = accDao.getAll()
            if (accounts.isEmpty()) {
                //da li ovdje da trazim email i id accounte preko api-ja?
                accDao.insertAccount(Account(0,"student",acHash))
            }
            return@withContext accDao.getHash()
        }
    }
}