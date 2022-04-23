package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository

class AnketeViewModel {
    fun dajAnkete(): List<Anketa> {
        return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
    }

    fun filtriraj(odabranaOpcija: String, opcije: Array<String>): List<Anketa> {
        when(odabranaOpcija) {
            opcije[0] -> {
                return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
            }
            opcije[1] -> {
                return AnketaRepository.getAll().sortedBy { it.datumPocetak  }
            }
            opcije[2] -> {
                return AnketaRepository.getDone().sortedBy { it.datumPocetak  }
            }
            opcije[3] -> {
                return AnketaRepository.getFuture().sortedBy { it.datumPocetak  }
            }
            opcije[4] -> {
                return AnketaRepository.getNotTaken().sortedBy { it.datumPocetak  }
            }
        }
        return emptyList()
    }

    fun dajProgres(nazivAnkete: String, nazivIstrazivanja: String): String {
        val anketa = AnketaRepository.dajAnketu(nazivAnkete,nazivIstrazivanja)
        val progres = ((anketa?.dajProgresZaokruzen() ?: 0f).toInt()*100).toString()+"%"
        return progres
    }
}