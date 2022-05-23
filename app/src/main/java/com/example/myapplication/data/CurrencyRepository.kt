package com.example.myapplication.data

import androidx.lifecycle.LiveData
import com.example.myapplication.domain.model.Currency

interface CurrencyRepository {

    suspend fun getAll(): List<Currency>

    suspend fun createCurrencyItem(currency: Currency, onSuccess: () -> Unit)

    suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess: () -> Unit)

    suspend fun updateListCurrency(currency: Currency, onSuccess: () -> Unit)

}