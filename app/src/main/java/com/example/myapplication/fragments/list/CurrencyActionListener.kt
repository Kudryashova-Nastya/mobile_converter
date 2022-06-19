package com.example.myapplication.fragments.list

import com.example.myapplication.domain.model.Currency

interface CurrencyActionListener {

    fun onCurrencyFavorite(currency: Currency)

//    fun currencyExchange(currency: Currency)
}