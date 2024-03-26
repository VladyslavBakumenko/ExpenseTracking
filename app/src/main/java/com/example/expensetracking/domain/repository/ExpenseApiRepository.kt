package com.example.expensetracking.domain.repository

import com.example.expensetracking.data.network.dto.CryptocurrencyPriceData
import kotlinx.coroutines.flow.Flow

interface ExpenseApiRepository {

    suspend fun getBtcPrice(): Flow<CryptocurrencyPriceData>
}