package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object IstrazivanjeIGrupaRepository {
    private lateinit var context: Context

    fun setContext(_context: Context) {
        context = _context
    }

    suspend fun getIstrazivanja(offset: Int): List<Istrazivanje> {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.dajSvaIstrazivanja(offset)
            var responseBody: List<Istrazivanje>? = response.body()
            if(responseBody == null) {
                responseBody = mutableListOf()
            }
            upisiIstrazivanjaUBazu(responseBody)
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
            upisiIstrazivanjaUBazu(istrazivanjaList)
            return@withContext istrazivanjaList
        }
    }
    suspend fun getIstrazivanjaBaza(): List<Istrazivanje>{
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            return@withContext db.istrazivanjeDao().getIstrazivanja()
        }
    }

    private suspend fun upisiIstrazivanjaUBazu(istrazivanjaList: List<Istrazivanje>) {
        val db = AppDatabase.getInstance(context)
        db.istrazivanjeDao().insertIstrazivanje(*istrazivanjaList.toTypedArray())
    }

    suspend fun getGrupe(): List<Grupa> {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.dajSveGrupe()
            val sveGrupe = response.body() ?: mutableListOf<Grupa>()
            upisiGrupeUBazu(sveGrupe)
            return@withContext sveGrupe
        }
    }
    suspend fun getGrupeBaza(): List<Grupa> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            return@withContext db.grupaDao().getGrupe()
        }
    }
    private suspend fun upisiGrupeUBazu(grupe: List<Grupa>) {
        val db = AppDatabase.getInstance(context)
        db.grupaDao().insertGrupa(*grupe.toTypedArray())
    }

    suspend fun getGrupeZaIstrazivanje(idIstrazivanja: Int): List<Grupa> {
        return withContext(Dispatchers.IO){
            val sveGrupe = getGrupe()
            return@withContext sveGrupe.filter { grupa -> grupa.istrazivanjeId == idIstrazivanja }
        }
    }
    suspend fun getGrupeZaIstrazivanjeBaza(idIstrazivanja: Int): List<Grupa> {
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(context)
            return@withContext db.grupaDao().getGrupeZaIstrazivanje(idIstrazivanja)
        }
    }
    suspend fun upisiUGrupu(idGrupa: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val idStudent = AccountRepository.getHash()
            val response = ApiAdapter.retrofit.dodajStudentaUGrupu(idStudent, idGrupa)
            val responseBodyObject = response.body()
            val poruka = responseBodyObject?.poruka ?: ""
//            Log.i("TEST", "Istrazivanje i grupa repository upis " + idGrupa + " poruka " + poruka)

            if (poruka == "Grupa not found" || poruka.contains("Ne postoji account"))
                return@withContext false
            upisiUGrupuBaza(idGrupa)
            return@withContext true
        }
    }

    private suspend fun upisiUGrupuBaza(idGrupa: Int) {
        val db = AppDatabase.getInstance(context)
        db.grupaDao().upisiUGrupu(idGrupa)
    }

    suspend fun getUpisaneGrupe(): List<Grupa> {
        return withContext(Dispatchers.IO){
            try {
                val studentId = AccountRepository.getHash()
                val response = ApiAdapter.retrofit.dajGrupeZaStudenta(studentId)
                var responseBody = response.body() ?: mutableListOf()
                responseBody.forEach { grupa -> grupa.upisana = true }
                upisiGrupeUBazu(responseBody)
                return@withContext responseBody
            } catch (exception: IllegalStateException) {
//                Log.i("TEST"," greska")
                return@withContext mutableListOf<Grupa>()
            }
        }
    }
    suspend fun getUpisaneGrupeBaza(): List<Grupa> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            return@withContext db.grupaDao().getUpisaneGrupe()
        }
    }

    suspend fun dajNeupisanaIstrazivanja(): List<Istrazivanje> {
        return getIstrazivanja().minus(getUpisanaIstrazivanja())
    }
    suspend fun dajNeupisanaIstrazivanjaBaza(): List<Istrazivanje> {
        return getIstrazivanjaBaza().minus(getUpisanaIstrazivanjaBaza())
    }

    private suspend fun getUpisanaIstrazivanjaBaza(): List<Istrazivanje> {
        return withContext(Dispatchers.IO) {
            val upisaneGrupe = getUpisaneGrupeBaza()
            val istrazivanja = getIstrazivanjaBaza()
            val upisana = mutableListOf<Istrazivanje>()
            istrazivanja.forEach {
                val grupeZaIstrazivanje = getGrupeZaIstrazivanjeBaza(it.id)
                if(grupeZaIstrazivanje.any {
                    grupa -> upisaneGrupe.any { grupa2 -> grupa2.id == grupa.id }
                    }) upisana.add(it)
            }
            return@withContext upisana
        }
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
    suspend fun dajNeupisanaIstrazivanjaZaGodinuBaza(odabranaGodina: Int): List<Istrazivanje> {
        return dajNeupisanaIstrazivanjaBaza().filter { istrazivanje -> istrazivanje.godina == odabranaGodina }
    }
    var posljednjaOdabranaGodina = 1
}