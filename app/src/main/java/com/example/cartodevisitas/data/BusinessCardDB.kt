package com.example.cartodevisitas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 1, exportSchema = false)
abstract class BusinessCardDB : RoomDatabase() {

    companion object {
        private const val DB_NAME =  "businesscard_db"
        @Volatile
        private var INSTANCE: BusinessCardDB? = null

        fun getDataBase(context: Context): BusinessCardDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BusinessCardDB::class.java,
                    DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun businessCardDAO(): BusinessCardDAO
}