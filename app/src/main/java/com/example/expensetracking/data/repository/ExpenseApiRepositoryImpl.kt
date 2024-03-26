package com.example.expensetracking.data.repository

import com.example.expensetracking.data.local.db.ExpenseDao
import com.example.expensetracking.data.local.model.ExpenseDBModel
import com.example.expensetracking.data.network.api.ApiService
import com.example.expensetracking.data.network.dto.CryptocurrencyPriceData
import com.example.expensetracking.domain.repository.ExpenseApiRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class ExpenseApiRepositoryImpl @Inject constructor(private val apiService: ApiService): ExpenseApiRepository {

    override suspend fun getBtcPrice(): CryptocurrencyPriceData {
        return apiService.getCryptocurrencyPrice("f")
    }
}