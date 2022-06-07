package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.ApiAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AnketaRepository {

//    fun getMyAnkete(): List<Anketa> {
//        val myAnkete = ArrayList<Anketa>()
//        for(grupa in GrupaRepository.upisaneGrupe){
//            for(anketa in ankete){
//                if (anketa.nazivGrupe==grupa.naziv && anketa.nazivIstrazivanja==grupa.nazivIstrazivanja)
//                    myAnkete.add(anketa)
//            }
//        }
//        return myAnkete.sortedBy { anketa -> anketa.datumPocetak }
//        //mozel ovo krace?
//    }
//    fun getAll(): List<Anketa> {
//        return ankete
//    }
//    fun getDone(): List<Anketa> {
//        return getMyAnkete().filter { anketa -> anketa.datumRada!=null }
//    }
//    fun getFuture(): List<Anketa>{
//        val now: Date = Calendar.getInstance().time
//        return getMyAnkete().filter { anketa -> anketa.datumPocetak.after(now) }
//    }
//    //sve prosle koje nisu uradjene
//    fun getNotTaken(): List<Anketa>{
//        val now: Date = Calendar.getInstance().time
//        return getMyAnkete().filter { anketa -> anketa.datumRada==null && anketa.datumKraj.before(now) }
//    }

//    fun dajAnketu(nazivAnkete: String, nazivIstrazivanja: String): Anketa? {
//        return ankete.find { anketa -> anketa.naziv == nazivAnkete && anketa.nazivIstrazivanja == nazivIstrazivanja }
//    }
//
//    fun azurirajProgresZaAnketu(anketa: Anketa, progres: Float) {
////        Log.i("TAGTAG", anketa.naziv + " " + anketa.nazivIstrazivanja + " " + progres)
//        dajAnketu(anketa.naziv,anketa.nazivIstrazivanja)!!.progres = progres
//    }

    suspend fun getAll(offset: Int): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val response = ApiAdapter.retrofit.dajSveAnkete(offset)
            var responseBody: List<Anketa>? = response.body()
            if(responseBody == null) {
                responseBody = mutableListOf()
            }
            return@withContext popuniIstrazivanjaZaAnkete(responseBody)
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
            return@withContext popuniIstrazivanjaZaAnkete(anketaList)
        }
    }

    private suspend fun popuniIstrazivanjaZaAnkete(ankete: List<Anketa>): List<Anketa> {
        val anketeSaIstrazivanjem = mutableListOf<Anketa>()
        for (anketa in ankete) {
            var grupeZaAnketu = ApiAdapter.retrofit.dajGrupeZaAnketu(anketa.id).body()
            grupeZaAnketu = grupeZaAnketu?.distinctBy { it.istrazivanjeId }
            //pazi da li je grupaid bitan u anketi
            val tempList = mutableListOf<Anketa>()
            grupeZaAnketu!!.forEach { grupa ->
                val istrazivanje = ApiAdapter.retrofit.dajIstrazivanje(grupa.istrazivanjeId).body()!!
                tempList.add(Anketa(anketa.id, anketa.naziv, anketa.datumPocetak, anketa.datumKraj, anketa.trajanje, istrazivanje.id, istrazivanje.naziv, grupa.id, grupa.naziv,0,false))
            }
            anketeSaIstrazivanjem.addAll(tempList)
        }
        //postavi i progres
        anketeSaIstrazivanjem.forEach { anketa -> anketa.progres = TakeAnketaRepository.dajPokusajZaAnketu(anketa.id)?.progres ?: 0 }
        return anketeSaIstrazivanjem
    }

    suspend fun getById(id: Int): Anketa {
        return withContext(Dispatchers.IO) {
            // dal u try catch
            val response = ApiAdapter.retrofit.dajAnketu(id)
            return@withContext response.body()!!
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
            return@withContext popuniIstrazivanjaZaAnkete(upisaneAnkete)
        }
    }
}
