package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Currencies
import com.example.myapplication.domain.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() { // надстройка с бизнеслогикой

//    val liveData = MutableLiveData<CurrenciesUiModel>()
    val liveData = MutableLiveData<Currencies>()

    fun init() {
        viewModelScope.launch {
            repository.getCurrencies()?.let {
//                liveData.postValue(CurrencyUiModelMapper.mapDomainModelToUiModel(it))
                liveData.postValue(it)
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