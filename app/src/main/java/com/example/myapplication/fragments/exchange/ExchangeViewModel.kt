package com.example.myapplication.fragments.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.data.room.Currency
import com.example.myapplication.data.room.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun addHistory(history: History, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.addHistory(history){
                onSuccess()
            }
        }
    }
}


