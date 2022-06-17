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

    suspend fun isAnketaPredana(anketa: Anketa, pitanja: List<Pitanje>): Boolean {
        val dosadasnjiOdgovori = OdgovorRepository.getOdgovoriAnketa(anketa.id)
        Log.i("TEST", "tu sam 1")
        return dosadasnjiOdgovori.size == pitanja.size
    }

    fun postaviOdgovore(mapaPitanjeOdgovor: MutableMap<Pitanje, Int?>, anketaId: Int, action: (poruka: String) -> Unit, poruka: String ) {
        scope.launch {
            Log.i("PREDAJ", " " + anketaId)
            val pokusaj = TakeAnketaRepository.zapocniAnketu(anketaId)
            for (entry in mapaPitanjeOdgovor) {
                if (entry.value != null)
                    OdgovorRepository.postaviOdgovorAnketa(
                        pokusaj!!.id,
                        entry.key.id,
                        entry.value!!
                    )
            }
            action.invoke(poruka)
        }
    }
}
