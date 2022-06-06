package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {
    suspend fun getIstrazivanja(offset: Int): List<Istrazivanje> {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.dajSvaIstrazivanja(offset)
            var responseBody: List<Istrazivanje>? = response.body()
            if(responseBody == null) {
                responseBody = mutableListOf()
            }
            return@withContext responseBody
        }
    }
    suspend fun getIstrazivanja(): List<Istrazivanje>{
        return withContext(Dispatchers.IO) {
            var i = 1
            val istrazivanjaList = mutableListOf<Istrazivanje>()
            do {
                val response = ApiAdapter.retrofit.dajSvaIstrazivanja(i)
                response.body()?.let { istrazivanjaList.addAll(it) }
                i++
            } while (response.body()!!.size == 5)
            return@withContext istrazivanjaList
        }
    }
    suspend fun getGrupe(): List<Grupa> {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.dajSveGrupe()
            return@withContext response.body() ?: mutableListOf<Grupa>()
        }
    }
    suspend fun getGrupeZaIstrazivanje(idIstrazivanja: Int): List<Grupa> {
        return withContext(Dispatchers.IO){
            var sveGrupe = getGrupe()
            return@withContext sveGrupe.filter { grupa -> grupa.istrazivanjeId == idIstrazivanja }
        }
    }
    suspend fun upisiUGrupu(idGrupa: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val idStudent = AccountRepository.getHash()
            val response = ApiAdapter.retrofit.dodajStudentaUGrupu(idStudent, idGrupa)
            val responseBodyObject = response.body()
            val poruka = responseBodyObject?.poruka ?: ""
            Log.i("TEST", "Istrazivanje i grupa repository upis " + idGrupa + " poruka " + poruka)

            if (poruka == "Grupa not found" || poruka.contains("Ne postoji account"))
                return@withContext false
            return@withContext true
        }
    }
    suspend fun getUpisaneGrupe(): List<Grupa> {
        return withContext(Dispatchers.IO){
            try {
                val studentId = AccountRepository.getHash()
                Log.i("TEST", "tu sam 1")
                val response = ApiAdapter.retrofit.dajGrupeZaStudenta(studentId)
//            var responseBody = response.body() ?: mutableListOf()
            return@withContext response.body() ?: mutableListOf()
            } catch (exception: IllegalStateException) {
                Log.i("TEST"," greska")
                return@withContext mutableListOf<Grupa>()
            }
        }
    }

    suspend fun dajNeupisanaIstrazivanja(): List<Istrazivanje> {
        return getIstrazivanja().minus(getUpisanaIstrazivanja())
    }

    private suspend fun getUpisanaIstrazivanja(): List<Istrazivanje> {
        return withContext(Dispatchers.IO) {
            val upisaneGrupe = getUpisaneGrupe()
            val svaIstrazivanja = getIstrazivanja()
            val upisanaIstrazivanja = mutableListOf<Istrazivanje>()
            svaIstrazivanja.forEach {
                val grupeZaIstrazivanje = getGrupeZaIstrazivanje(it.id)
                if(grupeZaIstrazivanje.any {
                    g1 -> upisaneGrupe.any { g2 -> g1.id == g2.id }
                    }) upisanaIstrazivanja.add(it)
            }
            return@withContext upisanaIstrazivanja
        }
    }

    suspend fun dajNeupisanaIstrazivanjaZaGodinu(odabranaGodina: Int): List<Istrazivanje> {
        return dajNeupisanaIstrazivanja().filter { istrazivanje -> istrazivanje.godina == odabranaGodina }
    }

    var posljednjaOdabranaGodina = 1
}