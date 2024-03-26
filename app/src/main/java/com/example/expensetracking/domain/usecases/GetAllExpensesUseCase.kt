package com.example.expensetracking.domain.usecases

import com.example.expensetracking.domain.repository.ExpenseDatabaseRepository
import javax.inject.Inject

class GetAllExpensesUseCase @Inject constructor(private val repository: ExpenseDatabaseRepository) {

    operator fun invoke() = repository.getAllExpenses()
}