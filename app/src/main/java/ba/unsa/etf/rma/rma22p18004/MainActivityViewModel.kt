package ba.unsa.etf.rma.rma22p18004

class MainActivityViewModel {
    fun dajFilterOpcije(): List<String>{
        return listOf("Sve moje ankete",
        "Sve ankete",
        "Urađene ankete",
        "Buduće ankete",
        "Prošle (neurađene) ankete")
    }
    fun dajAnkete(): List<Anketa> {
        return AnketaRepository.getMyAnkete()
    }
}