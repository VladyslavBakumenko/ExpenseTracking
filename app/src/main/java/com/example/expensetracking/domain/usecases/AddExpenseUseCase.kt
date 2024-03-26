package com.example.expensetracking.domain.usecases

import com.example.expensetracking.data.local.model.ExpenseDBModel
import com.example.expensetracking.domain.repository.ExpenseDatabaseRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(private val repository: ExpenseDatabaseRepository) {

    operator fun invoke(expenseDBModel: ExpenseDBModel) = repository.addExpense(expenseDBModel)
}