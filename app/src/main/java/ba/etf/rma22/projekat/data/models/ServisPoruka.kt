package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class ServisPoruka(
    @SerializedName("message") val poruka: String
) {

}
