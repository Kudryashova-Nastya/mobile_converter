package com.example.myapplication.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.AppDatabase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<CurrencyDb?>>
    private val repository: RoomCurrencyRepository

    init {
        val nodesDAO = AppDatabase.getDatabase(application).getCurrencyDao()
        repository = RoomCurrencyRepository(nodesDAO)
        readAllData = nodesDAO.getAll()
    }


    fun addCurrency(node: CurrencyDb) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createCurrency(node)
        }
    }
}