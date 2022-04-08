package ba.unsa.etf.rma.rma22p18004.viewmodel

import ba.unsa.etf.rma.rma22p18004.models.GrupaRepository
import ba.unsa.etf.rma.rma22p18004.models.IstrazivanjeRepository

class UpisIstrazivanjeViewModel {
    fun dajIstrazivanja(odabranaGodina: String): List<String>{
        return IstrazivanjeRepository.dajIstrazivanjaNaKojaNijeUpisan(Integer.parseInt(odabranaGodina)).map { istrazivanje -> istrazivanje.naziv }
    }
    fun dajGrupe(odabranoIstrazivanje: String): List<String>{
        return GrupaRepository.getGroupsByIstrazivanjet(odabranoIstrazivanje).map { grupa -> grupa.naziv }
    }
    fun upisiIstrazivanje(odabranoIstrazivanje: String, odabranaGrupa: String, odabranaGodina: String) {
        IstrazivanjeRepository.upisiIstrazivanja(odabranoIstrazivanje,Integer.parseInt(odabranaGodina))
        GrupaRepository.upisiGrupu(odabranaGrupa, odabranoIstrazivanje)
    }

}