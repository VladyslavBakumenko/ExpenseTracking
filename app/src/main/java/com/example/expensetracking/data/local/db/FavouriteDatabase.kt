package com.example.expensetracking.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expensetracking.data.local.model.ExpenseDBModel

@Database(entities = [ExpenseDBModel::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        private const val DB_NAME = "ExpenseDatabase"
        private var INSTANCE: ExpenseDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): ExpenseDatabase {
            INSTANCE?.let { return it }

            val database = Room.databaseBuilder(
                context = context,
                klass = ExpenseDatabase::class.java,
                name = DB_NAME
            ).build()

            INSTANCE = database
            return database
        }
    }
}