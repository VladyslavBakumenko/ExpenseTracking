package com.example.expensetracking.data.api.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/coin/{uuid}/price")
    suspend fun getCryptocurrencyPrice(
        @Path("uuid") uuid: String
    )


}