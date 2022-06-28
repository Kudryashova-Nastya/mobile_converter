package com.example.myapplication.fragments.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.data.room.History
import com.example.myapplication.domain.model.Currencies
import kotlinx.coroutines.runBlocking

class HistoryViewModel(private var realization: RoomCurrencyRepository) : ViewModel() {
    val data: MutableLiveData<Currencies> by lazy {
        MutableLiveData()
    }

    fun getHistory(): List<History>? {
        return runBlocking {
            realization.getHistory()
        }
    }


}


