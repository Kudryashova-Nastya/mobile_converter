package com.example.myapplication.fragments.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.RoomCurrencyRepository

class HistoryViewModelFactory(private val repositoryRealization: RoomCurrencyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(this.repositoryRealization) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}