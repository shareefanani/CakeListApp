package com.waracle.cakelistapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CakeRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/") // Update with your actual base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val cakeApi: CakeApi = retrofit.create(CakeApi::class.java)

    fun getCakes() = cakeApi.getCakes()
}
