package com.example.expensetracking.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class ExpenseDBModel(
    @PrimaryKey val id : Int,
)