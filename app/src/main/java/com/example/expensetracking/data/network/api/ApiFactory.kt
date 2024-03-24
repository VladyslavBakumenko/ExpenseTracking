package com.example.expensetracking.data.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiFactory {

    private const val BASE_URL = "https://api.coinranking.com/v2"
    private const val KEY_PARAM = "key"
    private const val btcUuid = "Qwsogvtv82FCd"


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val newUrl = originalRequest.url().newBuilder().addQueryParameter(
                KEY_PARAM,
                "coinranking832552b070d3e460631b182d39290fbf1fab5b022073a061"
            ).build()
            val newRequest = originalRequest.newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService: ApiService = retrofit.create()
}