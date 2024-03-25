package com.example.expensetracking.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.expensetracking.data.local.model.ExpenseDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert
    fun addExpense(expense: ExpenseDBModel)

    @Query("SELECT * FROM expense")
    fun getAllExpenses(): Flow<List<ExpenseDBModel>>
}