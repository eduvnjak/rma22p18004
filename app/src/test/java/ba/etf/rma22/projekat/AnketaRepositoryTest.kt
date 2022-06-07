package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import org.junit.Assert
import org.junit.Test

class AnketaRepositoryTest {
    val anketaRep = AnketaRepository
//    @Test
//    fun test1(){
//        val sveMojeAnkete = anketaRep.getMyAnkete()
//        val ostaleAnkete = anketaRep.getAll().minus(sveMojeAnkete)
//        val upisaneGrupe = GrupaRepository.upisaneGrupe
//        for (anketa in sveMojeAnkete){
//            Assert.assertTrue(upisaneGrupe.contains(Grupa(anketa.nazivGrupe, anketa.nazivIstrazivanja)))
//        }
//        for (anketa in ostaleAnkete){
//            Assert.assertFalse(upisaneGrupe.contains(Grupa(anketa.nazivGrupe, anketa.nazivIstrazivanja)))
//        }
//    }
//    @Test
//    fun test2(){
//        val uradjeneAnkete = anketaRep.getDone()
//        val sviOstali = anketaRep.getMyAnkete().minus(uradjeneAnkete)
//        for (anketa in uradjeneAnkete){
//            Assert.assertTrue(anketa.dajStatusAnkete() == 1)
//        }
//        for(anketa in sviOstali){
//            Assert.assertTrue(anketa.dajStatusAnkete() != 1)
//        }
//    }
//    @Test
//    fun test3(){
//        val buduceAnkete = anketaRep.getFuture()
//        val sviOstali = anketaRep.getMyAnkete().minus(buduceAnkete)
//        for (anketa in buduceAnkete){
//            Assert.assertTrue(anketa.dajStatusAnkete() == 3)
//        }
//        for (anketa in sviOstali){
//            Assert.assertTrue(anketa.dajStatusAnkete() != 3)
//        }
//    }
//    @Test
//    fun test4(){
//        val prosleAnkete = anketaRep.getNotTaken()
//        val sviOstali = anketaRep.getMyAnkete().minus(prosleAnkete)
//        for (anketa in prosleAnkete){
//            Assert.assertTrue(anketa.dajStatusAnkete() == 4)
//        }
//        for (anketa in sviOstali){
//            Assert.assertTrue(anketa.dajStatusAnkete() != 4)
//        }
//    }
}