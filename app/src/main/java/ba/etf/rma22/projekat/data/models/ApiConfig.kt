package ba.etf.rma22.projekat.data.models

object ApiConfig {
    var baseUrl = "https://rma22ws.herokuapp.com "

    fun postaviBaseURL(baseUrl: String): Unit {
        this.baseUrl = baseUrl
    }
}