package ba.unsa.etf.rma.rma22p18004

class MainActivityViewModel {
    fun dajAnkete(): List<Anketa> {
        return AnketaRepository.getMyAnkete()
    }

    fun filtriraj(odabranaOpcija: String): List<Anketa> {
        when(odabranaOpcija) {
            "Sve moje ankete" -> {
                return AnketaRepository.getMyAnkete()
            }
            "Sve ankete" -> {
                return AnketaRepository.getAll()
            }
            "Urađene ankete" -> {
                return AnketaRepository.getDone()
            }
            "Buduće ankete" -> {
                return AnketaRepository.getFuture()
            }
            "Prošle (neurađene) ankete" -> {
                return AnketaRepository.getNotTaken()
            }
        }
        return emptyList()
    }
}