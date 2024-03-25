package com.example.expensetracking

import android.app.Application
import com.example.expensetracking.di.ApplicationComponent
import com.example.expensetracking.di.DaggerApplicationComponent

class ExpenseApp : Application(){

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}