package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaGrupa
import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object AnketaRepository {
    private lateinit var context: Context
    fun setContext(_context: Context) {
        context = _context
    }
    suspend fun getAll(offset: Int): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.dajSveAnkete(offset)
            var responseBody: List<Anketa>? = response.body()
            if(responseBody == null) {
                responseBody = mutableListOf<Anketa>()
            }
            upisiAnketeUBazu(responseBody)
//            return@withContext popuniIstrazivanjaZaAnkete(responseBody)
            return@withContext responseBody
        }
    }

    private suspend fun upisiAnketeUBazu(ankete: List<Anketa>?) {
        return withContext(Dispatchers.IO) {
            if (ankete != null) {
                val db = AppDatabase.getInstance(context)
                db.anketaDao().insertAnkete(*ankete.toTypedArray())
            }
        }
    }

    suspend fun getAll(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            var i = 1
            val anketaList = mutableListOf<Anketa>()
            do {
                val response = ApiAdapter.retrofit.dajSveAnkete(i)
                response.body()?.let { anketaList.addAll(it) }
                i++
            } while (response.body()!!.size == 5)
//            return@withContext popuniIstrazivanjaZaAnkete(anketaList)
            upisiAnketeUBazu(anketaList)
            return@withContext anketaList
        }
    }
    suspend fun getAllBaza(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            val lista = db.anketaDao().getAll()
            if (lista != null){
                return@withContext lista
            }
            return@withContext mutableListOf<Anketa>()
        }
    }
    suspend fun popuniIstrazivanjaZaAnkete(ankete: List<Anketa>): List<Anketa> {
         val anketeSaIstrazivanjem = mutableListOf<Anketa>()
         val listaAnketaGrupa = mutableListOf<AnketaGrupa>()
         for (anketa in ankete) {
             var grupeZaAnketu = ApiAdapter.retrofit.dajGrupeZaAnketu(anketa.id).body()
             grupeZaAnketu?.forEach { listaAnketaGrupa.add(AnketaGrupa(anketa.id, it.id)) }
             grupeZaAnketu = grupeZaAnketu?.distinctBy { it.istrazivanjeId }
             //pazi da li je grupaid bitan u anketi
             val tempList = mutableListOf<Anketa>()
             grupeZaAnketu!!.forEach { grupa ->
                 val istrazivanje = ApiAdapter.retrofit.dajIstrazivanje(grupa.istrazivanjeId).body()!!
                 tempList.add(Anketa(anketa.id, anketa.naziv, anketa.datumPocetak, anketa.datumKraj, anketa.trajanje, istrazivanje.id, istrazivanje.naziv, grupa.id, grupa.naziv,0,false, null))
             }
             anketeSaIstrazivanjem.addAll(tempList)
         }
         //postavi i progres
         anketeSaIstrazivanjem.forEach { anketa -> anketa.progres = TakeAnketaRepository.dajPokusajZaAnketu(anketa.id)?.progres ?: 0 }
         //postavi i datum rada
         anketeSaIstrazivanjem.forEach { anketa -> anketa.datumRada = TakeAnketaRepository.dajPokusajZaAnketu(anketa.id)?.datumRada }
         //upisi grupaAnkete
         upisiAnketaGrupeUBazu(listaAnketaGrupa)
         return anketeSaIstrazivanjem
    }

    private suspend fun upisiAnketaGrupeUBazu(listaAnketaGrupa: List<AnketaGrupa>) {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            db.anketaGrupaDao().insertAnketaGrupa(*listaAnketaGrupa.toTypedArray())
        }
    }

    suspend fun popuniIstrazivanjaZaAnketeBaza(ankete: List<Anketa>): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            val anketeSaIstrazivanjem = mutableListOf<Anketa>()
            for (anketa in ankete) {
                val listaAnketaGrupa = db.anketaGrupaDao().getAnketaGrupaZaAnketu(anketa.id)
                val skupIstrazivanja = mutableSetOf<Istrazivanje>()
                listaAnketaGrupa.forEach {
                    val grupa = db.grupaDao().getGrupaById(it.grupaId)
                    val istrazivanje =
                        db.istrazivanjeDao().getIstrazivanjeById(grupa.istrazivanjeId)
                    if (istrazivanje != null) {
                        skupIstrazivanja.add(istrazivanje)
                    }
                }
                val tempList = mutableListOf<Anketa>()
                for (istrazivanje in skupIstrazivanja) {
                    tempList.add(
                        Anketa(
                            anketa.id,
                            anketa.naziv,
                            anketa.datumPocetak,
                            anketa.datumKraj,
                            anketa.trajanje,
                            istrazivanje.id,
                            istrazivanje.naziv,
                            null,
                            null,
                            0,
                            false,
                            null
                        )
                    )
                }
                anketeSaIstrazivanjem.addAll(tempList)
            }
            //postavi datumrada i progres
            anketeSaIstrazivanjem.forEach {
                val pokusaj = TakeAnketaRepository.dajPokusajZaAnketuBaza(it.id)
                it.progres = pokusaj?.progres ?: 0
                it.datumRada = pokusaj?.datumRada
            }
            return@withContext anketeSaIstrazivanjem
        }
    }
    suspend fun getById(id: Int): Anketa {
        return withContext(Dispatchers.IO) {
            // dal u try catch
            val response = ApiAdapter.retrofit.dajAnketu(id)
            upisiAnketuUBazu(response.body())
            return@withContext response.body()!!
        }
    }
    //ovu iznad iz baze???
    private suspend fun upisiAnketuUBazu(anketa: Anketa?) {
        return withContext(Dispatchers.IO) {
            if (anketa != null) {
                val db = AppDatabase.getInstance(context)
                db.anketaDao().insertAnkete(anketa)
            }
        }
    }

    suspend fun getUpisane(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val upisaneAnkete = mutableListOf<Anketa>()
            val upisaneGrupe = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            if (upisaneGrupe.isNotEmpty()) {
                for (grupa in upisaneGrupe) {
                    //ovo dole u try catch
                    val anketeZaGrupu = ApiAdapter.retrofit.dajAnketeZaGrupu(grupa.id).body()
                    if (anketeZaGrupu != null) {
                        upisaneAnkete.addAll(anketeZaGrupu)
                    }
                }
            }
            upisiUpisaneUBazu(upisaneAnkete)
            //return@withContext popuniIstrazivanjaZaAnkete(upisaneAnkete)
            return@withContext upisaneAnkete
        }
    }

    private suspend fun upisiUpisaneUBazu(upisaneAnkete: List<Anketa>) {
        return withContext(Dispatchers.IO) {
            for (anketa in upisaneAnkete) anketa.upisana = true
            val db = AppDatabase.getInstance(context)
            db.anketaDao().insertAnkete(*upisaneAnkete.toTypedArray())
        }
    }

    suspend fun getUpisaneBaza(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(context)
            return@withContext db.anketaDao().getUpisane()
        }
    }
}
