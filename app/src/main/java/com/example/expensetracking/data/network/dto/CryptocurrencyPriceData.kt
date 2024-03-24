package com.example.expensetracking.data.network.dto

import com.google.gson.annotations.SerializedName

data class CryptocurrencyPriceData(
    @SerializedName("price") val status: Double,
    @SerializedName("timestamp") val data: Long,
)