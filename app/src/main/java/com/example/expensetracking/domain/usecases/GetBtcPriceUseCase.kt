package com.example.expensetracking.domain.usecases

import com.example.expensetracking.domain.repository.ExpenseApiRepository
import javax.inject.Inject

class GetBtcPriceUseCase @Inject constructor(
    private val repository: ExpenseApiRepository
) {
    suspend operator fun invoke() = repository.getBtcPrice()
}
