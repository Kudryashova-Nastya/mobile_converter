package com.example.myapplication.data


data class CurrencyResponse(
    val sucess: Boolean,
    val timestamp: Long,
    val date: String,
    val rates: Map<String, Double>
)