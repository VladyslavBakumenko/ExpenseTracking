package com.example.expensetracking.domain.repository

import com.example.expensetracking.data.network.dto.CryptocurrencyPriceData

interface ExpenseApiRepository {

    suspend fun getBtcPrice(): CryptocurrencyPriceData
}