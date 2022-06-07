package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import org.junit.Assert
import org.junit.Test

class IstrazivanjaGrupeTest {

    val istrazivanjeRep = IstrazivanjeRepository
    val grupaRep = GrupaRepository
//    @Test
//    fun testPredmetiSaGodina(){
//        val istrazivanjaSaGodine1 = istrazivanjeRep.dajIstrazivanjaNaKojaNijeUpisan(1)
//        val ocekivaniSaPrve = listOf(Istrazivanje("Istrazivanje 2",1),
//                                    Istrazivanje("Istrazivanje 3",1),)
//        Assert.assertTrue(istrazivanjaSaGodine1.size==ocekivaniSaPrve.size && istrazivanjaSaGodine1.containsAll(ocekivaniSaPrve) && ocekivaniSaPrve.containsAll(istrazivanjaSaGodine1))
//        val istrazivanjaSaGodine2 = istrazivanjeRep.dajIstrazivanjaNaKojaNijeUpisan(2)
//        Assert.assertTrue(istrazivanjaSaGodine2.isEmpty())
//        val istrazivanjaSaGodine3 = istrazivanjeRep.dajIstrazivanjaNaKojaNijeUpisan(3)
//        val ocekivaniSaTrece = listOf(Istrazivanje("Istrazivanje 6",3))
//        Assert.assertTrue(istrazivanjaSaGodine3.size == ocekivaniSaTrece.size && istrazivanjaSaGodine3.containsAll(ocekivaniSaTrece) && ocekivaniSaTrece.containsAll(istrazivanjaSaGodine3))
//        val istrazivanjaSaGodine4 = istrazivanjeRep.dajIstrazivanjaNaKojaNijeUpisan(4)
//        Assert.assertTrue(istrazivanjaSaGodine4.isEmpty())
//        val istrazivanjaSaGodine5 = istrazivanjeRep.dajIstrazivanjaNaKojaNijeUpisan(5)
//        Assert.assertTrue(istrazivanjaSaGodine5.isEmpty())
//    }
//    @Test
//    fun testGrupeSaPredmeta(){
//        val grupe1 = grupaRep.getGroupsByIstrazivanje("Istrazivanje 1")
//        val ocekivaneGrupe1 = listOf(
//            Grupa("I1 G1","Istrazivanje 1"),
//            Grupa("I1 G2","Istrazivanje 1"),
//        )
//        Assert.assertTrue(grupe1.size==ocekivaneGrupe1.size && grupe1.containsAll(ocekivaneGrupe1) && ocekivaneGrupe1.containsAll(grupe1))
//        val grupe2 = grupaRep.getGroupsByIstrazivanje("Istrazivanje 2")
//        val ocekivaneGrupe2 = listOf(
//            Grupa("I2 G1","Istrazivanje 2"),
//            Grupa("I2 G2","Istrazivanje 2")
//        )
//        Assert.assertTrue(grupe2.size==ocekivaneGrupe2.size && grupe2.containsAll(ocekivaneGrupe2) && ocekivaneGrupe2.containsAll(grupe2))
//        val grupe3 = grupaRep.getGroupsByIstrazivanje("Istrazivanje 4")
//        val ocekivaneGrupe3 = listOf(
//            Grupa("I4 G1", "Istrazivanje 4"),
//            Grupa("I4 G2", "Istrazivanje 4"),
//        )
//        Assert.assertTrue(grupe3.size==ocekivaneGrupe3.size && grupe3.containsAll(ocekivaneGrupe3) && ocekivaneGrupe3.containsAll(grupe3))
//    }
}