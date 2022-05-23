package com.example.myapplication.data

import android.app.Application
import android.content.Context

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