package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.ApiAdapter
import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

object PitanjeAnketaRepository {
//    fun odgovoriNaPitanje(naziv: String, anketa: String, istrazivanje: String, odgovor: Int?) {
//        getPitanjeAnketa(anketa,istrazivanje,naziv).odabranaOpcija = odgovor
//    }
//
//    fun izracunajProgres(nazivAnkete: String, nazivIstrazivanja: String): Float {
//        val svaPitanja = pitanjaAnkete.filter { pitanjeAnketa -> pitanjeAnketa.anketa == nazivAnkete && pitanjeAnketa.istrazivanje == nazivIstrazivanja }
//        val ukupnoPitanja = svaPitanja.size
//        val odgovoreno = svaPitanja.count { pitanjeAnketa -> pitanjeAnketa.odabranaOpcija != null }
////        Log.i("TAGTAG", ukupnoPitanja.toString() + " " + odgovoreno)
//        return odgovoreno.toFloat()/ukupnoPitanja
//    }
    suspend fun getPitanja(idAnkete: Int): List<Pitanje> {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiAdapter.retrofit.dajPitanjaZaAnketu(idAnkete)
                return@withContext response.body()!!
            }catch (exception: Exception) {
                return@withContext emptyList<Pitanje>()
            }
        }
    }
}