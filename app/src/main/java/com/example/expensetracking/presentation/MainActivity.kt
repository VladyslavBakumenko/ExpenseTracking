package com.example.expensetracking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.expensetracking.presentation.ui.theme.ExpenseTrackingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackingTheme {

            }
        }
    }
}
