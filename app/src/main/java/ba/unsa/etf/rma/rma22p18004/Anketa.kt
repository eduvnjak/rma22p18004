package ba.unsa.etf.rma.rma22p18004

import java.util.*

data class Anketa(val naziv: String, val nazivIstrazivanja: String, val datumPocetak: Date,
                  val datumKraj: Date, var datumRada: Date?, val trajanje: Int, val nazivGrupe: String, var progres: Float){
    init {
        require((progres >= 0) and (progres <= 1)) {
            "progres mora biti izmedju 0 i 1 ukljucivo"
        }
    }
}


