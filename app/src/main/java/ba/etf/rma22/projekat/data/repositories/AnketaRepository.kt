package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*
import kotlin.collections.ArrayList

object AnketaRepository {
    val ankete: MutableList<Anketa> = ArrayList<Anketa>()

    init {
        val calendar = Calendar.getInstance()
        calendar.set(2022,2,7)
        var datum1: Date = calendar.time
        calendar.set(2022,2,15)
        var datum2: Date = calendar.time
        calendar.set(2022,2,9)
        var datum3 = calendar.time

        ankete.add(
            Anketa("Anketa 1 I1 G1",
            "Istrazivanje 1",
            datum1,
            datum2,
            datum3,
            5,
            "I1 G1",
            1f)
        )
        ankete.add(
            Anketa("Anketa 1 I1 G2",
            "Istrazivanje 1",
            datum1,
            datum2,
            null,
            5,
            "I1 G2",
            0f)
        )
        calendar.set(2022,2,8)
        datum1 = calendar.time
        calendar.set(2022,2,15)
        datum2 = calendar.time
        ankete.add(
            Anketa("Anketa 2 I1 G1",
            "Istrazivanje 1",
            datum1,
            datum2,
            null,
            2,
            "I1 G1",
            0.4f)
        )
        calendar.set(2022,7,10)
        datum1 = calendar.time
        calendar.set(2022,7,15)
        datum2 = calendar.time
        ankete.add(
            Anketa("Anketa 1 I4 G1",
            "Istrazivanje 4",
            datum1,
            datum2,
            null,
            7,
            "I4 G1",
            0f)
        )
        ankete.add(
            Anketa("Anketa 2 I1 G2",
                "Istrazivanje 1",
                datum1,
                datum2,
                null,
                10,
                "I1 G2",
                0f)
        )
        calendar.set(2022,2,10)
        datum1 = calendar.time
        calendar.set(2022,6,15)
        datum2 = calendar.time
        ankete.add(
            Anketa("Anketa 1 I5 G1",
            "Istrazivanje 5",
            datum1,
            datum2,
            null,
            2,
            "I5 G1",
            0.13f)
        )
        calendar.set(2022,3,1)
        datum1 = calendar.time
        calendar.set(2022,5,5)
        datum2 = calendar.time
        calendar.set(2022,3,3)
        datum3 = calendar.time
        ankete.add(
            Anketa("Anketa 2 I5 G1",
                "Istrazivanje 5",
                datum1,
                datum2,
                datum3,
                2,
                "I5 G1",
                1f)
        )
        calendar.set(2022,2,8)
        datum1 = calendar.time
        calendar.set(2022,2,15)
        datum2 = calendar.time
        ankete.add(
            Anketa("Anketa 6 I6 G1",
            "Istrazivanje 6",
            datum1,
            datum2,
            null,
            2,
            "I6 G1",
            0f)
        )
        ankete.add(
            Anketa("Anketa 6 I6 G2",
            "Istrazivanje 6",
            datum1,
            datum2,
            null,
            2,
            "I6 G2",
            0f)
        )
        calendar.set(2022,7,10)
        datum1 = calendar.time
        calendar.set(2022,7,15)
        datum2 = calendar.time
        ankete.add(
            Anketa("Anketa 1 I2 G1",
            "Istrazivanje 2",
            datum1,
            datum2,
            null,
            7,
            "I2 G1",
            0f)
        )
        ankete.add(
            Anketa("Anketa 1 I3 G1",
            "Istrazivanje 3",
            datum1,
            datum2,
            null,
            7,
            "I3 G1",
            0f)
        )
        calendar.set(2022,2,10)
        datum1 = calendar.time
        calendar.set(2022,7,15)
        datum2 = calendar.time
        ankete.add(
            Anketa("Anketa 2 I2 G1",
            "Istrazivanje 2",
            datum1,
            datum2,
            null,
            2,
            "I2 G1",
            0f)
        )
        ankete.add(
            Anketa("Anketa 1 I2 G2",
                "Istrazivanje 2",
                datum1,
                datum2,
                null,
                2,
                "I2 G2",
                0f)
        )
        ankete.add(
            Anketa("Anketa 1 I3 G2",
            "Istrazivanje 3",
            datum1,
            datum2,
            null,
            2,
            "I3 G2",
            0f)
        )
        ankete.add(
            Anketa("Anketa 2 I3 G1",
                "Istrazivanje 3",
                datum1,
                datum2,
                null,
                2,
                "I3 G1",
                0f)
        )
        ankete.add(
            Anketa("Anketa 1 I4 G2",
            "Istrazivanje 4",
            datum1,
            datum2,
            null,
            2,
            "I4 G2",
            0f)
        )
    }

    fun getMyAnkete(): List<Anketa> {
        val myAnkete = ArrayList<Anketa>()
        for(grupa in GrupaRepository.upisaneGrupe){
            for(anketa in ankete){
                if (anketa.nazivGrupe==grupa.naziv && anketa.nazivIstrazivanja==grupa.nazivIstrazivanja)
                    myAnkete.add(anketa)
            }
        }
        return myAnkete
        //mozel ovo krace?
    }
    fun getAll(): List<Anketa> {
        return ankete
    }
    fun getDone(): List<Anketa> {
        return getMyAnkete().filter { anketa -> anketa.datumRada!=null }
    }
    fun getFuture(): List<Anketa>{
        val now: Date = Calendar.getInstance().time
        return getMyAnkete().filter { anketa -> anketa.datumPocetak.after(now) }
    }
    //sve prosle koje nisu uradjene
    fun getNotTaken(): List<Anketa>{
        val now: Date = Calendar.getInstance().time
        return getMyAnkete().filter { anketa -> anketa.datumRada==null && anketa.datumKraj.before(now) }
    }
}
