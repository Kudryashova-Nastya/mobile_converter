package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.ui.mapper.CurrencyUiModelMapper
import com.example.myapplication.ui.model.CurrenciesUiModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() { // надстройка с бизнеслогикой

    val liveData = MutableLiveData<CurrenciesUiModel>()

    fun init() {
        viewModelScope.launch {
            repository.getCurrencies()?.let {
                liveData.postValue(CurrencyUiModelMapper.mapDomainModelToUiModel(it)) // возвращает
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