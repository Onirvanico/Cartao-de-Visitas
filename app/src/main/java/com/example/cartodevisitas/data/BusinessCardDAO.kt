package com.example.cartodevisitas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BusinessCardDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessCard: BusinessCard)

    @Query("SELECT * FROM BusinessCard")
    fun getBusinessCards(): LiveData<List<BusinessCard>>
}