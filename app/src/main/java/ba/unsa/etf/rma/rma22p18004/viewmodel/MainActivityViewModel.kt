package ba.unsa.etf.rma.rma22p18004.viewmodel

import ba.unsa.etf.rma.rma22p18004.models.Anketa
import ba.unsa.etf.rma.rma22p18004.models.AnketaRepository

class MainActivityViewModel {
    fun dajAnkete(): List<Anketa> {
        return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
    }

    fun filtriraj(odabranaOpcija: String, opcije: Array<String>): List<Anketa> {
        when(odabranaOpcija) {
            opcije[1] -> {
                return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
            }
            opcije[2] -> {
                return AnketaRepository.getAll().sortedBy { it.datumPocetak  }
            }
            opcije[3] -> {
                return AnketaRepository.getDone().sortedBy { it.datumPocetak  }
            }
            opcije[4] -> {
                return AnketaRepository.getFuture().sortedBy { it.datumPocetak  }
            }
            opcije[5] -> {
                return AnketaRepository.getNotTaken().sortedBy { it.datumPocetak  }
            }
        }
        return emptyList()
    }
}