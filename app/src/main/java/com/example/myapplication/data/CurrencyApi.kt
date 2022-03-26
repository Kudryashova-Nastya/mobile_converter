package com.example.myapplication.data

import retrofit2.http.GET

interface CurrencyApi {

    @GET("/api/latest?access_key=47d4755eb33fe9e632c096fff1832b1f")
    suspend fun getCurrencies(): CurrencyResponse

//    @GET("/api/convert")
//    suspend fun convert()
}