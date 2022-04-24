package ba.etf.rma22.projekat.data.repositories

import android.util.Log
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa

object PitanjeAnketaRepository {
    val svaPitanja: List<Pitanje> = listOf(
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
        )),

        Pitanje("pitanje7","tekst pitanja 7", listOf(
            "opcija 1",
            "opcija 2",
        )),
        Pitanje("pitanje8","tekst pitanja 8", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),

        Pitanje("pitanje9","tekst pitanja 9", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje10","tekst pitanja 10", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje11","tekst pitanja 11", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),

        Pitanje("pitanje12","tekst pitanja 12", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje13","tekst pitanja 13", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje14","tekst pitanja 14", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje15","tekst pitanja 15", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),

        Pitanje("pitanje16","tekst pitanja 16", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje17","tekst pitanja 17", listOf(
            "opcija 1",
            "opcija 2",
        )),

        Pitanje("pitanje18","tekst pitanja 18", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje19","tekst pitanja 19", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3"
        )),

        Pitanje("pitanje20","tekst pitanja 20", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje21","tekst pitanja 21", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3"
        )),
        Pitanje("pitanje22","tekst pitanja 22", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje23","tekst pitanja 23", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3"
        )),
        Pitanje("pitanje24","tekst pitanja 24", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje25","tekst pitanja 25", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje26","tekst pitanja 26", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3"
        )),
        Pitanje("pitanje27","tekst pitanja 27", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje28","tekst pitanja 28", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3"
        )),
        Pitanje("pitanje29","tekst pitanja 29", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje30","tekst pitanja 30", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3"
        )),
        Pitanje("pitanje31","tekst pitanja 31", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje32","tekst pitanja 32", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje33","tekst pitanja 33", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje34","tekst pitanja 34", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje35","tekst pitanja 35", listOf(
            "opcija 1",
            "opcija 2",
        )),
        Pitanje("pitanje36","tekst pitanja 36", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje37","tekst pitanja 37", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje38","tekst pitanja 38", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje39","tekst pitanja 39", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje40","tekst pitanja 40", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),
        Pitanje("pitanje41","tekst pitanja 41", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),
        Pitanje("pitanje42","tekst pitanja 42", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje43","tekst pitanja 43", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
        )),

        Pitanje("pitanje44","tekst pitanja 44", listOf(
            "opcija 1",
            "opcija 2",
        )),

        Pitanje("pitanje45","tekst pitanja 45", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
        )),

        Pitanje("pitanje46","tekst pitanja 46", listOf(
            "opcija 1",
            "opcija 2",
            "opcija 3",
            "opcija 4",
            "opcija 5"
        )),
    )
    val pitanjaAnkete: List<PitanjeAnketa> = listOf(
        PitanjeAnketa("pitanje1","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje2","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje3","Anketa 1 I5 G1" ,"Istrazivanje 5",0),
        PitanjeAnketa("pitanje4","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje5","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
        PitanjeAnketa("pitanje6","Anketa 1 I5 G1" ,"Istrazivanje 5",null),

        PitanjeAnketa("pitanje7","Anketa 1 I1 G1" ,"Istrazivanje 1",1),
        PitanjeAnketa("pitanje8","Anketa 1 I1 G1" ,"Istrazivanje 1",0),

        PitanjeAnketa("pitanje9","Anketa 1 I1 G2" ,"Istrazivanje 1",null),
        PitanjeAnketa("pitanje10","Anketa 1 I1 G2" ,"Istrazivanje 1",null),
        PitanjeAnketa("pitanje11","Anketa 1 I1 G2" ,"Istrazivanje 1",null),

        PitanjeAnketa("pitanje12","Anketa 2 I1 G1" ,"Istrazivanje 1",0),
        PitanjeAnketa("pitanje13","Anketa 2 I1 G1" ,"Istrazivanje 1",1),
        PitanjeAnketa("pitanje14","Anketa 2 I1 G1" ,"Istrazivanje 1",2),
        PitanjeAnketa("pitanje15","Anketa 2 I1 G1" ,"Istrazivanje 1",null),

        PitanjeAnketa("pitanje16","Anketa 1 I6 G1" ,"Istrazivanje 6",null),
        PitanjeAnketa("pitanje17","Anketa 1 I6 G1" ,"Istrazivanje 6",null),

        PitanjeAnketa("pitanje18","Anketa 1 I6 G2" ,"Istrazivanje 6",null),
        PitanjeAnketa("pitanje19","Anketa 1 I6 G2" ,"Istrazivanje 6",null),

//        PitanjeAnketa("pitanje20","Anketa 1 I5 G1" ,"Istrazivanje 5",3),
//        PitanjeAnketa("pitanje21","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
//        PitanjeAnketa("pitanje22","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
//        PitanjeAnketa("pitanje23","Anketa 1 I5 G1" ,"Istrazivanje 5",null),
//        PitanjeAnketa("pitanje24","Anketa 1 I5 G1" ,"Istrazivanje 5",null),

        PitanjeAnketa("pitanje25","Anketa 2 I2 G1" ,"Istrazivanje 2",null),
        PitanjeAnketa("pitanje26","Anketa 2 I2 G1" ,"Istrazivanje 2",null),
        PitanjeAnketa("pitanje27","Anketa 2 I2 G1" ,"Istrazivanje 2",null),
        PitanjeAnketa("pitanje28","Anketa 2 I2 G1" ,"Istrazivanje 2",null),
        PitanjeAnketa("pitanje29","Anketa 2 I2 G1" ,"Istrazivanje 2",null),

        PitanjeAnketa("pitanje30","Anketa 1 I2 G2" ,"Istrazivanje 2",null),
        PitanjeAnketa("pitanje31","Anketa 1 I2 G2" ,"Istrazivanje 2",null),

        PitanjeAnketa("pitanje32","Anketa 1 I3 G2" ,"Istrazivanje 3",null),
        PitanjeAnketa("pitanje33","Anketa 1 I3 G2" ,"Istrazivanje 3",null),
        PitanjeAnketa("pitanje34","Anketa 1 I3 G2" ,"Istrazivanje 3",null),

        PitanjeAnketa("pitanje35","Anketa 2 I3 G1" ,"Istrazivanje 3",null),
        PitanjeAnketa("pitanje36","Anketa 2 I3 G1" ,"Istrazivanje 3",null),
        PitanjeAnketa("pitanje37","Anketa 2 I3 G1" ,"Istrazivanje 3",null),

        PitanjeAnketa("pitanje38","Anketa 1 I4 G2" ,"Istrazivanje 4",null),
        PitanjeAnketa("pitanje39","Anketa 1 I4 G2" ,"Istrazivanje 4",null),

        PitanjeAnketa("pitanje40","Anketa 2 I5 G1" ,"Istrazivanje 5",0),
        PitanjeAnketa("pitanje41","Anketa 2 I5 G1" ,"Istrazivanje 5",1),
        PitanjeAnketa("pitanje42","Anketa 2 I5 G1" ,"Istrazivanje 5",2),

        PitanjeAnketa("pitanje43","Anketa 1 I4 G1" ,"Istrazivanje 4",null),

        PitanjeAnketa("pitanje44","Anketa 2 I1 G2" ,"Istrazivanje 1",null),

        PitanjeAnketa("pitanje45","Anketa 1 I2 G1" ,"Istrazivanje 2",null),

        PitanjeAnketa("pitanje46","Anketa 1 I3 G1" ,"Istrazivanje 3",null),

        )
    fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): List<Pitanje>{
        val pitanjaAnketeZaAnketu = pitanjaAnkete.filter { pitanjeAnketa -> pitanjeAnketa.anketa == nazivAnkete && pitanjeAnketa.istrazivanje == nazivIstrazivanja}
        val pitanja = ArrayList<Pitanje>()

        for(pitanjeAnketa in pitanjaAnketeZaAnketu){
            for(pitanje in svaPitanja){
                if(pitanje.naziv == pitanjeAnketa.naziv){
                    pitanja.add(pitanje)
                }
            }
        }
        return pitanja
    }

    fun getPitanjeAnketa(
        nazivAnkete: String,
        nazivIstrazivanja: String,
        nazivPitanja: String
    ): PitanjeAnketa {
        return pitanjaAnkete.find { pitanjeAnketa -> pitanjeAnketa.naziv == nazivPitanja && pitanjeAnketa.anketa == nazivAnkete && pitanjeAnketa.istrazivanje == nazivIstrazivanja }!!
    }

    fun odgovoriNaPitanje(naziv: String, anketa: String, istrazivanje: String, odgovor: Int?) {
        getPitanjeAnketa(anketa,istrazivanje,naziv).odabranaOpcija = odgovor
    }

    fun izracunajProgres(nazivAnkete: String, nazivIstrazivanja: String): Float {
        val svaPitanja = pitanjaAnkete.filter { pitanjeAnketa -> pitanjeAnketa.anketa == nazivAnkete && pitanjeAnketa.istrazivanje == nazivIstrazivanja }
        val ukupnoPitanja = svaPitanja.size
        val odgovoreno = svaPitanja.count { pitanjeAnketa -> pitanjeAnketa.odabranaOpcija != null }
//        Log.i("TAGTAG", ukupnoPitanja.toString() + " " + odgovoreno)
        return odgovoreno.toFloat()/ukupnoPitanja
    }
}