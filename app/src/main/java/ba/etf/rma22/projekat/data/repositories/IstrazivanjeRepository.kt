package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Istrazivanje

object IstrazivanjeRepository {
    val svaIstrazivanja: List<Istrazivanje> = listOf(
        Istrazivanje("Istrazivanje 1", 1),
        Istrazivanje("Istrazivanje 2", 1),
        Istrazivanje("Istrazivanje 3", 1),
        Istrazivanje("Istrazivanje 4", 2),
        Istrazivanje("Istrazivanje 5", 2),
        Istrazivanje("Istrazivanje 6", 3),
    )
    val upisanaIstrazivanja: MutableList<Istrazivanje> = mutableListOf(
        Istrazivanje("Istrazivanje 1", 1),
        Istrazivanje("Istrazivanje 4", 2),
        Istrazivanje("Istrazivanje 5", 2),
        )

    fun getIstrazivanjeByGodina(godina: Int): List<Istrazivanje>{
        return svaIstrazivanja.filter { istrazivanje -> istrazivanje.godina == godina  }
    }
    fun getAll(): List<Istrazivanje>{
        return svaIstrazivanja
    }
    fun getUpisani(): List<Istrazivanje>{
        return upisanaIstrazivanja
    }

    fun dajIstrazivanjaNaKojaNijeUpisan(godina: Int): List<Istrazivanje> {
        return getIstrazivanjeByGodina(godina).minus(
            upisanaIstrazivanja
        )
    }

    fun upisiIstrazivanja(nazivIstrazivanja: String, godinaIstrazivanja: Int) {
        upisanaIstrazivanja.add(svaIstrazivanja.find { istrazivanje -> istrazivanje.naziv==nazivIstrazivanja && istrazivanje.godina==godinaIstrazivanja }!!)
    }
}

//getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje> - lista istraživanja na godini
//getAll() : List<Istrazivanje> - lista svih istraživanja
//getUpisani() : List<Istrazivanje> - lista istraživanja na kojima je korisnik upisan
