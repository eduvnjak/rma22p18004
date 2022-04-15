package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa

object PitanjeAnketaRepository {
    val pitanja: List<Pitanje> = listOf(
        Pitanje("pitanje1","tekst pitanja 1", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje2","tekst pitanja 2", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje3","tekst pitanja 3", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje4","tekst pitanja 4", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
            "opcija 5",
        )),
        Pitanje("pitanje5","tekst pitanja 5", listOf(
            "opcija 1",
            "opcija 2",
        )),
        Pitanje("pitanje6","tekst pitanja 6", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        ))
    )
    val pitanjaAnkete: List<PitanjeAnketa> = listOf(
        PitanjeAnketa("pitanje1","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje2","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje3","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje4","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje5","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje6","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
    )
    fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): List<Pitanje>{
        val pitanja = ArrayList<Pitanje>()
        for(pitanjeAnketa in pitanjaAnkete){
            if(nazivAnkete == pitanjeAnketa.anketa && nazivIstrazivanja == pitanjeAnketa.istrazivanje){
                for(pitanje in pitanja){
                    if(pitanje.naziv == pitanjeAnketa.naziv){
                        pitanja.add(pitanje)
                        break
                    }
                }
            }
        }
        return pitanja
    }
}