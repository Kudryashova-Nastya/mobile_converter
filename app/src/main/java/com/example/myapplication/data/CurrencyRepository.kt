package com.example.myapplication.data

import com.example.myapplication.data.room.Currency
import com.example.myapplication.data.room.History

interface CurrencyRepository {

    suspend fun getAll(): List<Currency>

    suspend fun createCurrencyItem(currency: Currency, onSuccess: () -> Unit)

    suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess: () -> Unit)

    suspend fun updateListCurrency(currency: Currency, onSuccess: () -> Unit)

    suspend fun getFavoriteCurrencyList(): List<Currency>?

    suspend fun getRUB(): Currency

    suspend fun getHistory(): List<History>?

}