package com.example.myapplication.data.retrofit

import com.example.myapplication.data.CurrencyResponse
import retrofit2.http.GET

interface CurrencyApi {

    @GET("/api/latest?access_key=6cf6243a2a203eb73f19c0f419027207")
    suspend fun getRetrofitCurrencies(): CurrencyResponse

//    @GET("/api/convert")
//    suspend fun convert()
}