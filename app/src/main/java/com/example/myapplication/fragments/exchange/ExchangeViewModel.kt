package com.example.myapplication.fragments.exchange

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.data.room.Currency
import kotlinx.coroutines.runBlocking

class ExchangeViewModel(private var realization: RoomCurrencyRepository) : ViewModel() {

    fun getFavoriteCurrencyList(): List<Currency>? {
        return runBlocking {
            realization.getFavoriteCurrencyList()
        }
    }

    fun getRUB(): Currency {
        return runBlocking {
            realization.getRUB()
        }
    }
}


