package com.example.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DependencyInjection
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.data.room.Currency
import com.example.myapplication.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(val room: RoomCurrencyRepository) : ViewModel() { // надстройка с бизнеслогикой

    val liveData = MutableLiveData<List<Currency>>()
    var updateDate = ""

    private val repository: Repository = DependencyInjection.repository

    fun init() {
        this.getRetrofitCurrency()
    }


    private fun getLocalCurrencyList(): List<Currency> {
        return runBlocking {
            room.getAll()
        }
    }

    fun getRetrofitCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentListRoom = getLocalCurrencyList()

            // проверяем есть ли в бд данные, если нет, кладём их туда, если есть, то обновляем
            if (currentListRoom?.isEmpty() == true) {
                Log.d("MY_TAG_DB", "insert currencies")
                repository.getCurrencies()?.let { remoteCurrencies ->
                    remoteCurrencies.rates.map { remoteCurrency ->
                        insertCurrency(remoteCurrency) {}
                    }
                    updateDate = remoteCurrencies.date
                    liveData.postValue(getLocalCurrencyList())
                }
            } else {
                Log.d("MY_TAG_DB", "update currencies")
                liveData.postValue(currentListRoom!!)
                repository.getCurrencies()?.let { remoteCurrencies ->
                    remoteCurrencies.rates.map { remoteCurrency ->
                        updateListCurrency(remoteCurrency) {}
                    }
                    updateDate = remoteCurrencies.date
                    liveData.postValue(getLocalCurrencyList())
                }
            }
        }
    }


    fun insertCurrency(currency: Currency, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            room.createCurrencyItem(currency) {
                onSuccess()
            }
        }
    }

    fun updateListFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            room.updateListFavoriteCurrency(currency) {
                val newList = getLocalCurrencyList()
                liveData.postValue(newList!!)
                onSuccess()
            }
        }
    }

    private fun updateListCurrency(currency: Currency, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            room.updateListCurrency(currency) {
                onSuccess()
            }
        }
    }

//    fun update(index) {
//        var a = liveData.value
//
//        a[index] =
//        // прокинуть событие клика
//        // добавить элемент с закраш звездой в начало
//
//        liveData.postValue(a)
//    }
}