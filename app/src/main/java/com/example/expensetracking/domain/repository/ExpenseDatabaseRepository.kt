package com.example.expensetracking.domain.repository

import com.example.expensetracking.data.local.model.ExpenseDBModel
import kotlinx.coroutines.flow.Flow


interface ExpenseDatabaseRepository {

    fun addExpense(expenseDBModel: ExpenseDBModel)

    fun getAllExpenses(): Flow<List<ExpenseDBModel>>
}

