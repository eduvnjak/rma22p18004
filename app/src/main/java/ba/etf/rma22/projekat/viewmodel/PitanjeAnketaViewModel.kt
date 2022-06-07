package ba.etf.rma22.projekat.viewmodel

import android.util.Log
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PitanjeAnketaViewModel {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun dajPitanjaZaAnketu(anketa: Anketa, action: (anketa: Anketa, pitanjaZaAnketu: List<Pitanje>) ->Unit) {
        scope.launch {
            val pitanja = PitanjeAnketaRepository.getPitanja(anketa.id)
            action.invoke(anketa, pitanja)
        }
    }
    fun dajPitanjaZaAnketuIPokusaj(anketa: Anketa, action: (anketa: Anketa, pitanjaZaAnketu: List<Pitanje>, odgovori: List<Odgovor>) ->Unit) {
        scope.launch {
            val pitanja = PitanjeAnketaRepository.getPitanja(anketa.id)
            val odgovori = OdgovorRepository.getOdgovoriAnketa(anketa.id)
            action.invoke(anketa, pitanja, odgovori)
        }
    }
//    fun dajPitanjeAnketuZaAnketu(anketa: Anketa, pitanje: Pitanje): PitanjeAnketa {
//        return PitanjeAnketaRepository.getPitanjeAnketa(anketa.naziv, anketa.nazivIstrazivanja, pitanje.naziv)
//    }
//
//    fun azurirajOdgovor(pitanjeAnketa: PitanjeAnketa, odgovor: Int?) {
//        PitanjeAnketaRepository.odgovoriNaPitanje(pitanjeAnketa.naziv, pitanjeAnketa.anketa, pitanjeAnketa.istrazivanje, odgovor)
//    }
//    fun azurirajProgres(anketa: Anketa){
//        val progres = PitanjeAnketaRepository.izracunajProgres(anketa.naziv,anketa.nazivIstrazivanja)
//        AnketaRepository.azurirajProgresZaAnketu(anketa, progres)
//    }
    suspend fun isAnketaPredana(anketa: Anketa, pitanja: List<Pitanje>): Boolean {
        val dosadasnjiOdgovori = OdgovorRepository.getOdgovoriAnketa(anketa.id)
        Log.i("TEST", "tu sam 1")
        return dosadasnjiOdgovori.size == pitanja.size
    }
}
