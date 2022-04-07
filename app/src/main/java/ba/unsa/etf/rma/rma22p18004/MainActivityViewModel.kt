package ba.unsa.etf.rma.rma22p18004

class MainActivityViewModel {
    fun dajAnkete(): List<Anketa> {
        return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
    }

    fun filtriraj(odabranaOpcija: String): List<Anketa> {
        when(odabranaOpcija) {
            "Sve moje ankete" -> {
                return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak  }
            }
            "Sve ankete" -> {
                return AnketaRepository.getAll().sortedBy { it.datumPocetak  }
            }
            "Urađene ankete" -> {
                return AnketaRepository.getDone().sortedBy { it.datumPocetak  }
            }
            "Buduće ankete" -> {
                return AnketaRepository.getFuture().sortedBy { it.datumPocetak  }
            }
            "Prošle (neurađene) ankete" -> {
                return AnketaRepository.getNotTaken().sortedBy { it.datumPocetak  }
            }
        }
        return emptyList()
    }
}