package ba.etf.rma22.projekat.data.repositories

object AccountRepository {
    var acHash = "725a10c7-9c21-412d-a972-4c0468fbd599"

    fun postaviHash(acHash: String): Boolean {
        this.acHash = acHash
        return true
    }
    fun getHash(): String {
         return acHash
    }
}