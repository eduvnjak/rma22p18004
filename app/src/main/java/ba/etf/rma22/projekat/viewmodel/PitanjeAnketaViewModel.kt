package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository

class PitanjeAnketaViewModel {


//    fun dajPitanjaZaAnketu(anketa: Anketa): List<Pitanje> {
//        return PitanjeAnketaRepository.getPitanja(anketa.naziv, anketa.nazivIstrazivanja)
//    }
//
//    fun dajPitanjeAnketuZaAnketu(anketa: Anketa, pitanje: Pitanje): PitanjeAnketa {
//        return PitanjeAnketaRepository.getPitanjeAnketa(anketa.naziv, anketa.nazivIstrazivanja, pitanje.naziv)
//    }
//
//    fun azurirajOdgovor(pitanjeAnketa: PitanjeAnketa, odgovor: Int?) {
//        PitanjeAnketaRepository.odgovoriNaPitanje(pitanjeAnketa.naziv, pitanjeAnketa.anketa, pitanjeAnketa.istrazivanje, odgovor)
//    }
//    fun azurirajProgres(anketa: Anketa){
//        val progres = PitanjeAnketaRepository.izracunajProgres(anketa.naziv,anketa.nazivIstrazivanja)
//        AnketaRepository.azurirajProgresZaAnketu(anketa, progres)
//    }
    suspend fun isAnketaPredana(anketa: Anketa, pitanja: List<Pitanje>): Boolean {
        val dosadasnjiOdgovori = OdgovorRepository.getOdgovoriAnketa(anketa.id)
        return dosadasnjiOdgovori.size == pitanja.size
    }
}
