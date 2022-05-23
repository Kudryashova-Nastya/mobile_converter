package com.example.myapplication.data.retrofit

import com.example.myapplication.data.retrofit.CurrencyApi
import com.example.myapplication.data.CurrencyResponse

class RemoteDataSource(private val currencyApi: CurrencyApi) {
    suspend fun getCurrencies(): CurrencyResponse {
        return  currencyApi.getRetrofitCurrencies()
    }
}