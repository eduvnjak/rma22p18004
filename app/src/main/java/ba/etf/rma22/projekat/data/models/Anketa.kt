package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.math.round

data class Anketa(
    @SerializedName("id") val id: Int,
    @SerializedName("naziv") val naziv: String,
    @SerializedName("datumPocetak") val datumPocetak: Date,
    @SerializedName("datumKraj") val datumKraj: Date?,
    @SerializedName("trajanje") val trajanje: Int,
    var istrazivanjeId: Int?,
    var nazivIstrazivanja: String?,
    // da li ovdje da stavim i id istrazivanja????
    var grupaId: Int?,
    var nazivGrupe: String?,
    var progres: Int?,
    var predana: Boolean = false
)
//data class Anketa(
//    val naziv: String,
//    val nazivIstrazivanja: String,
//    val datumPocetak: Date,
//    val datumKraj: Date,
//    var datumRada: Date?,
//    val trajanje: Int,
//    val nazivGrupe: String,
//    var progres: Float){
//    init {
//        require((progres >= 0) and (progres <= 1)) {
//            "progres mora biti izmedju 0 i 1 ukljucivo"
//        }
//        //da li je ovo neophodno
//        //trajanje mora biti pozitivno
//        require(trajanje>0)
//        //datum pocetka mora biti prije datuma kraja
//        require(datumPocetak.before(datumKraj))
//        //datuma rada mora biti izmedju pocetka i kraja
//        if(datumRada != null){
//            require(datumPocetak.before(datumRada) and datumRada!!.before(datumKraj))
//            //uradjena anketa mora imati progres 1? ne mora !
//            //require(progres==1f)
//        }
//        //neuradjena anketa koja jos nije aktivna mora imati progres 0 ??
//        val now: Date = Calendar.getInstance().time
//        if(datumPocetak.after(now)){
//            require(progres==0f)
//        }
//    }
//    fun dajStatusAnkete(): Int{
//        if(datumRada != null) return 1 // uradjen
//        val trenutniDate = Calendar.getInstance().time
//        if(datumPocetak.after(trenutniDate)) return 3 //nije aktivan
//        if(datumKraj.after(trenutniDate)) return 2 // aktivan
//        return 4 // nije uradjen, prošao
//    }
//    fun dajDatumZaListu(): Date {
//        val status = this.dajStatusAnkete()
//        when(status){
//            1 -> return datumRada!!
//            3 -> return datumPocetak
//        }
//        return datumKraj
//    }
//    fun dajProgresZaokruzen(): Float{
//        if(progres > 0.8f && progres < 1f) return 0.8f
//        return round(progres/0.2f)*0.2f
//    }
//}


