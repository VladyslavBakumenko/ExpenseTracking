package com.example.expensetracking.di

import android.content.Context
import com.example.expensetracking.data.local.db.ExpenseDao
import com.example.expensetracking.data.local.db.ExpenseDatabase
import com.example.expensetracking.data.network.api.ApiFactory
import com.example.expensetracking.data.network.api.ApiService
import com.example.expensetracking.data.repository.ExpenseApiRepositoryImpl
import com.example.expensetracking.data.repository.ExpenseDatabaseRepositoryImpl
import com.example.expensetracking.domain.repository.ExpenseApiRepository
import com.example.expensetracking.domain.repository.ExpenseDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindExpenseApiRepository(impl: ExpenseApiRepositoryImpl): ExpenseApiRepository

    @[ApplicationScope Binds]
    fun bindExpenseDatabaseRepository(impl: ExpenseDatabaseRepositoryImpl): ExpenseDatabaseRepository


    companion object {

        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideExpenseDatabase(context: Context): ExpenseDatabase {
            return ExpenseDatabase.getInstance(context)
        }

        @[ApplicationScope Provides]
        fun provideExpenseDao(database: ExpenseDatabase): ExpenseDao {
            return database.expenseDao()
        }
    }
}