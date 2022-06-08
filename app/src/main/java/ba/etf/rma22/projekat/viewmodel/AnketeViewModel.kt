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
                    var ankete = AnketaRepository.getUpisane()
                    ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
//                    Log.i("TEST", "tu sam 2")
                    anketeAction.invoke(ankete)
                }
                //return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
            }
            opcije[1] -> {
                scope.launch {
                    var ankete = AnketaRepository.getAll()
                    ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
                    anketeAction.invoke(ankete)
                }
                //return AnketaRepository.getAll().sortedBy { it.datumPocetak  }
            }
            opcije[2] -> {
                scope.launch {
                    var ankete = AnketaRepository.getUpisane()
                    ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                    ankete.forEach {
                        it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                    }
                    anketeAction.invoke(ankete.filter { anketa -> anketa.predana })
                }
                //return AnketaRepository.getDone().sortedBy { it.datumPocetak  }
            }
            opcije[3] -> {
                scope.launch {
                    var ankete = AnketaRepository.getUpisane()
                    ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                    anketeAction.invoke(ankete.filter { anketa -> anketa.datumPocetak > trenutniDatum })
                }
                //return AnketaRepository.getFuture().sortedBy { it.datumPocetak  }
            }
            opcije[4] -> {
                scope.launch {
                    var ankete = AnketaRepository.getUpisane()
                    ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
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

    fun dajZaokruzenProgres(odgovoreno: Int, ukupno: Int): String {
        Log.i("PROGRES", "odgovoreno " + odgovoreno + " ukupno" + ukupno)
        if(ukupno != 0) {
            val omjer = odgovoreno.toFloat()/ukupno.toFloat()
            if(omjer >= 0 && omjer<0.1){
                return "0%"
            }else if (omjer >= 0.1 && omjer < 0.3){
                return "20%"
            }else if (omjer >= 0.3 && omjer < 0.5){
                return  "40%"
            }else if (omjer >= 0.5 && omjer < 0.7){
                return "60%"
            }else if (omjer >= 0.7 && omjer < 0.9){
                return "80%"
            }else return "100%"
        }else {
            return "0%"
        }
    }

    fun dajDatumZaUradjenuAnketu(anketaId: Int) {

    }
//    fun proglasiAnketuUradjenom(anketa: Anketa) {
//        val calendar = Calendar.getInstance()
//        AnketaRepository.dajAnketu(anketa.naziv, anketa.nazivIstrazivanja)!!.datumRada = calendar.time
//    }
}