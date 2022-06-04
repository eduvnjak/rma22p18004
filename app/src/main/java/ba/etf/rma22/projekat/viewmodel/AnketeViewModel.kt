package ba.etf.rma22.projekat.viewmodel

import android.util.Log
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class AnketeViewModel {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

//    fun dajAnkete(): List<Anketa> {
//        return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
//    }

    fun filtriraj(anketeAction: ((ankete: List<Anketa>) -> Unit),odabranaOpcija: String, opcije: Array<String>): List<Anketa> {
        val pitanjeAnketaViewModel = PitanjeAnketaViewModel()
        val trenutniDatum = Calendar.getInstance().time

        when(odabranaOpcija) {
            opcije[0] -> {
                scope.launch {
                    val ankete = AnketaRepository.getUpisane()
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
                    anketeAction.invoke(ankete)
                }
                //return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
            }
            opcije[1] -> {
                scope.launch {
                    val ankete = AnketaRepository.getAll()
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
                    anketeAction.invoke(ankete)
                }
                //return AnketaRepository.getAll().sortedBy { it.datumPocetak  }
            }
            opcije[2] -> {
                scope.launch {
                    val ankete = AnketaRepository.getUpisane()
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
                    anketeAction.invoke(ankete)
                }
                //return AnketaRepository.getDone().sortedBy { it.datumPocetak  }
            }
            opcije[3] -> {
                scope.launch {
                    val ankete = AnketaRepository.getUpisane()
                    anketeAction.invoke(ankete.filter { anketa -> anketa.datumPocetak > trenutniDatum })
                }
                //return AnketaRepository.getFuture().sortedBy { it.datumPocetak  }
            }
            opcije[4] -> {
                scope.launch {
                    val ankete = AnketaRepository.getUpisane()
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
                    anketeAction.invoke(ankete.filter { anketa -> anketa.datumKraj != null && trenutniDatum > anketa.datumKraj })
                }
                //return AnketaRepository.getNotTaken().sortedBy { it.datumPocetak  }
            }
        }
        return emptyList()
    }

//    fun dajProgres(anketa: Anketa): String {
//
//        val progres = (AnketaRepository.dajAnketu(anketa.naziv, anketa.nazivIstrazivanja)!!.dajProgresZaokruzen()*100).toInt().toString()+"%"
//        return progres
//    }

//    fun proglasiAnketuUradjenom(anketa: Anketa) {
//        val calendar = Calendar.getInstance()
//        AnketaRepository.dajAnketu(anketa.naziv, anketa.nazivIstrazivanja)!!.datumRada = calendar.time
//    }
}