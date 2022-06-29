package com.example.myapplication.domain.mapper

import com.example.myapplication.data.CurrencyResponse
import com.example.myapplication.domain.model.Currencies
import com.example.myapplication.data.room.Currency
import java.text.SimpleDateFormat
import java.util.*

object CurrencyDtoMapper {
    fun mapResponseToDomainModel(response: CurrencyResponse): Currencies {
        return Currencies(
            date = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault()).format(response.timestamp * 1000L + 10800000L),
            base = response.base,
            rates = response.rates.toList().map {
                Currency(it.first, it.second, false)
            }
        )
    }
}