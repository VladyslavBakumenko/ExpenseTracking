package com.example.expensetracking.data.dto

import com.google.gson.annotations.SerializedName

data class CryptocurrencyPriceResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: CryptocurrencyPriceData,
)