package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Currencies
import com.example.myapplication.domain.repository.Repository
import com.example.myapplication.ui.mapper.CurrencyUiModelMapper
import com.example.myapplication.ui.model.CurrenciesUiModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val liveData = MutableLiveData<CurrenciesUiModel>()

    fun init() {
        viewModelScope.launch {
            repository.getCurrencies()?.let {
                    liveData.postValue(CurrencyUiModelMapper.mapDomainModelToUiModel(it))
            }
        }
    }
}