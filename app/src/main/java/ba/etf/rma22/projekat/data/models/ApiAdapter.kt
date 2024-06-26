package ba.etf.rma22.projekat.data.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val retrofit : Api = Retrofit.Builder()
        .baseUrl(ApiConfig.baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}