package ba.etf.rma22.projekat.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AccountRepository {
    var acHash = "725a10c7-9c21-412d-a972-4c0468fbd599"

    suspend fun postaviHash(acHash: String): Boolean {
        return withContext(Dispatchers.IO){
            if(acHash != this@AccountRepository.acHash){
                this@AccountRepository.acHash = acHash
                obnoviBazu(acHash)
            }//treba li obnovu svakako uraditi?
            return@withContext true
        }
    }

    private fun obnoviBazu(acHash: String) {
        TODO("Not yet implemented")
    }

    fun getHash(): String {
         return acHash
    }
}