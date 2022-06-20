package com.example.myapplication.fragments.exchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.RoomCurrencyRepository


class ExchangeViewModelFactory(private val repositoryRealization: RoomCurrencyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExchangeViewModel::class.java)) {
            ExchangeViewModel(this.repositoryRealization) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}