package ba.unsa.etf.rma.rma22p18004.models

object GrupaRepository {
    val sveGrupe: List<Grupa> = arrayListOf(
        Grupa("I1 G1","Istrazivanje 1"),
        Grupa("I1 G2","Istrazivanje 1"),
        Grupa("I2 G1","Istrazivanje 2"),
        Grupa("I3 G1","Istrazivanje 3"),
        Grupa("I3 G2","Istrazivanje 3"),
        Grupa("I4 G1","Istrazivanje 4"),
        Grupa("I4 G2", "Istrazivanje 4"),
        Grupa("I5 G2","Istrazivanje 5"),
    )
    val upisaneGrupe: List<Grupa> = arrayListOf(
        Grupa("I1 G1","Istrazivanje 1"),
        Grupa("I4 G1","Istrazivanje 4"),
        Grupa("I5 G1","Istrazivanje 5"),
        )

    fun getGroupsByIstrazivanjet(nazivIstrazivanja:String) : List<Grupa>{
        return sveGrupe.filter { grupa -> grupa.nazivIstrazivanja == nazivIstrazivanja }
    }
}