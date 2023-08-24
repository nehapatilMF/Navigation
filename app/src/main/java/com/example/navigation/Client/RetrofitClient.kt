package com.example.navigation.Client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const  val baseUrl = "https://mutefrog.com/"
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}