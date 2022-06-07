package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
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
        return dosadasnjiOdgovori.size == pitanja.size
    }
}
