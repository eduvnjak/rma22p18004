package ba.etf.rma22.projekat.data.models

object ApiConfig {
    var baseURL = "https://rma22ws.herokuapp.com "

    fun postaviBaseURL(baseUrl: String) {
        this.baseURL = baseUrl
    }
}