package com.example.expensetracking.data.network.api

import com.example.expensetracking.data.network.dto.CryptocurrencyPriceData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/coin/{uuid}/price")
    suspend fun getCryptocurrencyPrice(
        @Path("uuid") uuid: String
    ): CryptocurrencyPriceData

}