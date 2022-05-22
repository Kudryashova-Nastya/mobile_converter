package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.model.Currency

interface CurrencyRepository {

    suspend fun getAll(): List<Currency>

    suspend fun createCurrencyItem(currency: Currency)

    suspend fun updateListFavoriteCurrency(currency: Currency)

    suspend fun updateListCurrency(currency: Currency)

}