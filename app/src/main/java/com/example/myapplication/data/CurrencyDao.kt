package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM Currency")
    fun getAll(): LiveData<List<CurrencyDb?>>

    @Insert(entity = CurrencyDb::class)
    suspend fun createCurrency(CurrencyDb: CurrencyDb)

}