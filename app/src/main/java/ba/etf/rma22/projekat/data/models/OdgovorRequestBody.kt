package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class OdgovorRequestBody(
    @SerializedName("odgovor") val odgovor: Int,
    @SerializedName("pitanje") val pitanjeId: Int,
    @SerializedName("progres") val progres: Int,
)
