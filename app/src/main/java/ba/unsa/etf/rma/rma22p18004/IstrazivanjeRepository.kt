package ba.unsa.etf.rma.rma22p18004

object IstrazivanjeRepository {
    val svaIstrazivanja: List<Istrazivanje> = arrayListOf(
        Istrazivanje("Istrazivanje 1", 1),
        Istrazivanje("Istrazivanje 2", 1),
        Istrazivanje("Istrazivanje 3", 1),
        Istrazivanje("Istrazivanje 4", 2),
        Istrazivanje("Istrazivanje 5", 2),
        Istrazivanje("Istrazivanje 6", 3),
    )
    val upisanaIstrazivanja: List<Istrazivanje> = arrayListOf(
        Istrazivanje("Istrazivanje 1", 1),
        Istrazivanje("Istrazivanje 4", 2),
        Istrazivanje("Istrazivanje 5", 2),
        )

    fun getIstrazivanjeByGodina(godina: Int): List<Istrazivanje>{
        return svaIstrazivanja.filter {istrazivanje -> istrazivanje.godina == godina  }
    }
    fun getAll(): List<Istrazivanje>{
        return svaIstrazivanja
    }
    fun getUpisani(): List<Istrazivanje>{
        return upisanaIstrazivanja
    }
}

//getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje> - lista istraživanja na godini
//getAll() : List<Istrazivanje> - lista svih istraživanja
//getUpisani() : List<Istrazivanje> - lista istraživanja na kojima je korisnik upisan
