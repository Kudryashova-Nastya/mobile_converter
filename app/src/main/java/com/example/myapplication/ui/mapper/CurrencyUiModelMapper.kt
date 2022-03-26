package com.example.myapplication.ui.mapper

import com.example.myapplication.domain.model.Currencies
import com.example.myapplication.ui.model.CurrenciesUiModel

object CurrencyUiModelMapper {
    fun mapDomainModelToUiModel(currencies: Currencies): CurrenciesUiModel {
        return CurrenciesUiModel(
            currencyList = currencies.rates,
            isLoading = true,
        )
    }
}