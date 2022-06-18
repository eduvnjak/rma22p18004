package ba.etf.rma22.projekat.viewmodel

import android.util.Log
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpisIstrazivanjeViewModel {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun upisiIstrazivanje(actionUpis: (string: String) -> Unit, poruka: String, odabranaGrupaId: Int, odabranaGodina: Int) {
        scope.launch {
            postaviPosljednjuOdabranuGodinu(odabranaGodina)
            //Log.i("TEST", "viewmodel upisi IStrazivanje")
            if(IstrazivanjeIGrupaRepository.upisiUGrupu(odabranaGrupaId)){
                actionUpis.invoke(poruka)
            }
        }
    }
    fun dajPosljednjuOdabranuGodinu(): String{
        return IstrazivanjeIGrupaRepository.posljednjaOdabranaGodina.toString()
    }
    fun postaviPosljednjuOdabranuGodinu(godina: Int){
        IstrazivanjeIGrupaRepository.posljednjaOdabranaGodina = godina
    }

    fun popuniIstrazivanjaZaGodinu(actionIstrazivanja: (istrazivanja: List<Istrazivanje>) -> Unit, odabranaGodina: Int) {
        scope.launch {
            actionIstrazivanja.invoke(dajIstrazivanjaZaGodinu(odabranaGodina))
            //actionIstrazivanja.invoke(emptyList<Istrazivanje>())
        }
    }

    private suspend fun dajIstrazivanjaZaGodinu(odabranaGodina: Int): List<Istrazivanje> {
        return IstrazivanjeIGrupaRepository.dajNeupisanaIstrazivanjaZaGodinu(odabranaGodina)
        //return emptyList()
    }

    fun popuniGrupeZaIstrazivanje(actionGrupe: (List<Grupa>) -> Unit, istrazivanjeId: Int) {
        scope.launch {
            actionGrupe.invoke((dajGrupeZaIstrazivanje(istrazivanjeId)))
            //actionGrupe.invoke(emptyList())
        }
    }

    private suspend fun dajGrupeZaIstrazivanje(istrazivanjeId: Int): List<Grupa> {
        //return emptyList()
        return IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(istrazivanjeId)
    }

    fun postaviAcountHash(payload: String) {
        scope.launch {
            AccountRepository.postaviHash(payload)
        }
    }
}