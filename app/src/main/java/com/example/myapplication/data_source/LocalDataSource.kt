package com.example.myapplication.data_source

import androidx.lifecycle.LiveData
import com.example.myapplication.data.CurrencyDao
import com.example.myapplication.data.CurrencyDb

class LocalDataSource(private val currencyDao: CurrencyDao) {
    fun getLocalCurrencies(): LiveData<List<CurrencyDb?>> {
        return  currencyDao.getAll()
    }
}