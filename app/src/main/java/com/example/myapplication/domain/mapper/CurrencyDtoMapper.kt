package com.example.myapplication.domain.mapper

import com.example.myapplication.data.CurrencyResponse
import com.example.myapplication.domain.model.Currencies
import com.example.myapplication.domain.model.Currency
import java.text.SimpleDateFormat

object CurrencyDtoMapper {
    fun mapResponseToDomainModel(response: CurrencyResponse): Currencies {
        return Currencies(
            date = SimpleDateFormat("yyyy-MM-dd").parse(response.date),
            base = response.base,
            rates = response.rates.toList().map {
                Currency(it.first, it.second)
            }
        )
    }
}