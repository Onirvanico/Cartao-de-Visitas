package com.example.cartodevisitas

import android.app.Application
import android.widget.Toast
import com.example.cartodevisitas.data.BusinessCardDB
import com.example.cartodevisitas.data.BusinessCardRepository
import com.google.android.material.snackbar.Snackbar

class App : Application() {

    val database by lazy { BusinessCardDB.getDataBase(this) }
    val repository by lazy { BusinessCardRepository(database.businessCardDAO()) }

    override fun onCreate() {
        super.onCreate()

    }
}