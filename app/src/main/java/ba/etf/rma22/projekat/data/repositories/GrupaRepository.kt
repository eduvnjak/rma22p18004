package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa

object GrupaRepository {
    val sveGrupe: List<Grupa> = listOf(
        Grupa("I1 G1","Istrazivanje 1"),
        Grupa("I1 G2","Istrazivanje 1"),
        Grupa("I2 G1","Istrazivanje 2"),
        Grupa("I2 G2","Istrazivanje 2"),
        Grupa("I3 G1","Istrazivanje 3"),
        Grupa("I3 G2","Istrazivanje 3"),
        Grupa("I4 G1","Istrazivanje 4"),
        Grupa("I4 G2","Istrazivanje 4"),
        Grupa("I5 G1","Istrazivanje 5"),
        Grupa("I6 G1","Istrazivanje 6"),
        Grupa("I6 G2","Istrazivanje 6"),
    )
    val upisaneGrupe: MutableList<Grupa> = mutableListOf(
        Grupa("I1 G1","Istrazivanje 1"),
        Grupa("I4 G1","Istrazivanje 4"),
        Grupa("I5 G1","Istrazivanje 5"),
        )

    fun getGroupsByIstrazivanje(nazivIstrazivanja:String) : List<Grupa>{
        return sveGrupe.filter { grupa -> grupa.nazivIstrazivanja == nazivIstrazivanja }
    }

    fun upisiGrupu(grupaNaziv: String, istrazivanjeNaziv: String) {
        upisaneGrupe.add(sveGrupe.find { grupa -> grupa.naziv==grupaNaziv && grupa.nazivIstrazivanja==istrazivanjeNaziv }!!)
    }
}