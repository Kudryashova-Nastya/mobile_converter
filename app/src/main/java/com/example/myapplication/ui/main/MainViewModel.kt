package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.DependencyInjection
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.domain.model.Currencies
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(val room: RoomCurrencyRepository) : ViewModel() { // надстройка с бизнеслогикой

//    val liveData = MutableLiveData<CurrenciesUiModel>()
    val liveData = MutableLiveData<Currencies>()

    private val repository: Repository = DependencyInjection.repository

    fun init() {
        this.getRetrofitCurrency()

    }

    fun getRetrofitCurrency(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCurrencies()?.let {
//                liveData.postValue(CurrencyUiModelMapper.mapDomainModelToUiModel(it))
                liveData.postValue(it)
            }
        }

    }

    fun getLocalCurrencyList(): List<Currency>{
        return runBlocking {
            room.getAll()
        }
    }


    fun insertCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            room.createCurrencyItem(currency){
                onSuccess()
            }
        }
    }

    fun updateListFavoriteCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            room.updateListFavoriteCurrency(currency){
                onSuccess()
            }
        }
    }

    fun updateListCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            room.updateListCurrency(currency){
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