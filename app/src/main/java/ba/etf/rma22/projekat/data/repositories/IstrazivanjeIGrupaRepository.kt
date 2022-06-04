package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {
    fun getIstrazivanja(offset: Int): List<Istrazivanje> {
        return emptyList()
    }
    fun getGrupe(): List<Grupa> {
        return emptyList()
    }
    fun getGrupeZaIstrazivanje(idIstrazivanja: Int): List<Grupa> {
        return emptyList()
    }
    fun upisiUGrupu(idGrupa: Int): Boolean {
        return false
    }
    suspend fun getUpisaneGrupe(): List<Grupa> {
        return withContext(Dispatchers.IO){
            val studentId = AccountRepository.getHash()
            val response = ApiAdapter.retrofit.dajGrupeZaStudenta(studentId)
//            var responseBody = response.body() ?: mutableListOf()
            return@withContext response.body() ?: mutableListOf()
        }
    }
}