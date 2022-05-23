package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.data.room.CurrencyDao

object RoomInitRepository {
    private lateinit var repositoryRealization: RoomCurrencyRepository
    private var currencyDao: CurrencyDao? = null

    fun getRepository(context: Context): RoomCurrencyRepository {
        if (currencyDao == null) {
            currencyDao = AppDatabase.getDatabase(context).getCurrencyDao()
            repositoryRealization = RoomCurrencyRepository(currencyDao!!)
        }
        return repositoryRealization
    }
}