package com.example.cartodevisitas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true)
     val id: Int = 0,
     val name: String,
     val phone: String,
     val email: String,
     val company_name: String,
     val cardBackground: String
)