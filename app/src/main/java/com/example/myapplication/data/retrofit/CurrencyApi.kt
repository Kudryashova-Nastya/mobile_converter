package com.example.myapplication.data.retrofit

import com.example.myapplication.data.CurrencyResponse
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/api/latest?access_key=623d1f82a98d130c16ed53e9fd1c026a")
    suspend fun getRetrofitCurrencies(): CurrencyResponse

//    @GET("/api/convert")
//    suspend fun convert()
}