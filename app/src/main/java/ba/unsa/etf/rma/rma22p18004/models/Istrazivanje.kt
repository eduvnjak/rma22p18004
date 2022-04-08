package ba.unsa.etf.rma.rma22p18004.models

data class Istrazivanje(val naziv: String, val godina: Int){
    init {
        require(godina in 1..5){
            "godina mora biti izmedju 1 i 5 ukljucivo"
        }
    }
}
