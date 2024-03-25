package com.example.expensetracking.data.repository

import com.example.expensetracking.data.local.db.ExpenseDao
import com.example.expensetracking.data.local.model.ExpenseDBModel
import com.example.expensetracking.domain.repository.ExpenseDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseDatabaseRepositoryImpl @Inject constructor(private val expenseDao: ExpenseDao): ExpenseDatabaseRepository {

    override fun addExpense(expenseDBModel: ExpenseDBModel) {
        expenseDao.addExpense(expenseDBModel)
    }

    override fun getAllExpenses(): Flow<List<ExpenseDBModel>> {
        return expenseDao.getAllExpenses()
    }
}