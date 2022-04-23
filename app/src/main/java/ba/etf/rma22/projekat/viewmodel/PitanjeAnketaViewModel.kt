package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository

class PitanjeAnketaViewModel {

    //da li su ove dvije potrebne
//    fun dajOpcije(nazivPitanja: String, nazivAnkete: String, nazivIstrazivanja: String): List<String> {
//        return PitanjeAnketaRepository.getPitanja(nazivAnkete, nazivIstrazivanja).find { pitanje -> pitanje.naziv == nazivPitanja }?.opcije
//            ?: emptyList()
//    }
//
//    fun dajTekstPitanja(nazivPitanja: String, nazivAnkete: String, nazivIstrazivanja: String): String {
//        return PitanjeAnketaRepository.getPitanja(nazivAnkete, nazivIstrazivanja).find { pitanje -> pitanje.naziv == nazivPitanja }?.tekst
//            ?: ""
//    }
    fun dajPitanjaZaAnketu(anketa: Anketa): List<Pitanje> {
        return PitanjeAnketaRepository.getPitanja(anketa.naziv, anketa.nazivIstrazivanja)
    }

    fun dajPitanjeAnketuZaAnketu(anketa: Anketa, pitanje: Pitanje): PitanjeAnketa {
        return PitanjeAnketaRepository.getPitanjeAnketa(anketa.naziv, anketa.nazivIstrazivanja, pitanje.naziv)
    }
}
