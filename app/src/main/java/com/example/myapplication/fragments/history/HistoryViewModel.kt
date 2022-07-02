package com.example.myapplication.fragments.history

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.data.room.History
import com.example.myapplication.domain.model.Currencies
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HistoryViewModel(private var room: RoomCurrencyRepository) : ViewModel() {
    val data: MutableLiveData<Currencies> by lazy {
        MutableLiveData()
    }

    val filter: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun getHistory(): List<History> {
        return runBlocking {
            room.getHistory()
        }
    }

    fun getMonthHistory(): List<History> {
        return runBlocking {
            room.getHistory().filter { checkMonth(it) }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun checkMonth(history: History): Boolean {
        return try {
            val date = LocalDateTime.parse(
                history.exchange_date,
                DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm", Locale.ENGLISH)
            )
            date.month == LocalDate.now().month
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("MY_TAG_ERROR", e.localizedMessage)
            false
        }
    }

}


