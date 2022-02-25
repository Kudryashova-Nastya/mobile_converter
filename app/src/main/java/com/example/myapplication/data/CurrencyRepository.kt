package com.example.myapplication.data

import androidx.lifecycle.LiveData

interface CurrencyRepository {

    suspend fun getAll(): LiveData<List<CurrencyDb?>>

    suspend fun createCurrency(addNewCurrency: CurrencyDb)

}