package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

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