package com.example.myapplication.data.retrofit

import com.example.myapplication.data.CurrencyResponse
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/api/latest?access_key=6e895dbf7bfbb3721de791f923e8a05c")
    suspend fun getRetrofitCurrencies(): CurrencyResponse

//    @GET("/api/convert")
//    suspend fun convert()
}