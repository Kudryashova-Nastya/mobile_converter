package com.example.myapplication.data_source

import com.example.myapplication.data.CurrencyApi
import com.example.myapplication.data.CurrencyResponse

class RemoteDataSource(private val currencyApi: CurrencyApi) {
    suspend fun getCurrencies(): CurrencyResponse {
        return  currencyApi.getCurrencies()
    }
}