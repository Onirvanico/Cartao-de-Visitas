package com.example.cartodevisitas

import android.app.Application
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Started", Toast.LENGTH_LONG).show()
    }
}