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

class AnketeViewModel(val offlineMode: Boolean) {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun filtriraj(anketeAction: ((ankete: List<Anketa>) -> Unit),odabranaOpcija: String, opcije: Array<String>): List<Anketa> {
        val pitanjeAnketaViewModel = PitanjeAnketaViewModel(offlineMode)
        val trenutniDatum = Calendar.getInstance().time

        when(odabranaOpcija) {
            opcije[0] -> {
                scope.launch {
                    var ankete: List<Anketa>
                    if(offlineMode){
                        ankete = AnketaRepository.getUpisaneBaza()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnketeBaza(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it, PitanjeAnketaRepository.getPitanjaBaza(it.id))
                        }
                    }else{
                        ankete = AnketaRepository.getUpisane()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                        }
                    }
                    anketeAction.invoke(ankete)
                }
            }
            opcije[1] -> {
                scope.launch {
                    var ankete: List<Anketa>
                    if(offlineMode){
                        ankete = AnketaRepository.getAllBaza()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnketeBaza(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it, PitanjeAnketaRepository.getPitanjaBaza(it.id))
                        }
                    }else{
                        ankete = AnketaRepository.getAll()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                        }
                    }
                    anketeAction.invoke(ankete)
                }
            }
            opcije[2] -> {
                scope.launch {
                    var ankete: List<Anketa>
                    if(offlineMode){
                        ankete = AnketaRepository.getUpisaneBaza()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnketeBaza(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it, PitanjeAnketaRepository.getPitanjaBaza(it.id))
                        }
                    }else{
                        ankete = AnketaRepository.getUpisane()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                        }
                    }
                    anketeAction.invoke(ankete.filter { anketa -> anketa.predana })
                }
            }
            opcije[3] -> {
                scope.launch {
                    var ankete: List<Anketa>
                    if(offlineMode){
                        ankete = AnketaRepository.getUpisaneBaza()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnketeBaza(ankete)
                    } else {
                        ankete = AnketaRepository.getUpisane()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                    }
                    anketeAction.invoke(ankete.filter { anketa -> anketa.datumPocetak > trenutniDatum })
                }
            }
            opcije[4] -> {
                scope.launch {
                    var ankete: List<Anketa>
                    if (offlineMode) {
                        ankete = AnketaRepository.getUpisaneBaza()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnketeBaza(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanjaBaza(it.id))
                        }
                    } else {
                        ankete = AnketaRepository.getUpisane()
                        ankete = AnketaRepository.popuniIstrazivanjaZaAnkete(ankete)
                        ankete.forEach {
                            it.predana = pitanjeAnketaViewModel.isAnketaPredana(it,PitanjeAnketaRepository.getPitanja(it.id))
                        }
                    }
                    anketeAction.invoke(ankete.filter { anketa -> anketa.datumKraj != null && trenutniDatum > anketa.datumKraj })
                }
            }
        }
        return emptyList()
    }

    fun dajZaokruzenProgres(odgovoreno: Int, ukupno: Int): String {
        //Log.i("PROGRES", "odgovoreno " + odgovoreno + " ukupno" + ukupno)
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


    fun anketaSeMozeIspuniti(anketa: Anketa): Boolean {
        if (anketa.predana) return false
        if (anketa.datumKraj != null) {
            val trenutnoVrijeme = Calendar.getInstance().time
            return anketa.datumKraj > trenutnoVrijeme
        }
        return true
    }

}