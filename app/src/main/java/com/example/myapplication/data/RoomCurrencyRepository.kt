package com.example.myapplication.data

import android.database.sqlite.SQLiteConstraintException
import com.example.myapplication.data.room.CurrencyDao
import com.example.myapplication.data.room.Currency
import com.example.myapplication.data.room.History

class RoomCurrencyRepository(
    private val currencyDao: CurrencyDao
) : CurrencyRepository {

    override suspend fun getAll(): List<Currency> {
        return currencyDao.getAll()
    }

    override suspend fun createCurrencyItem(currency: Currency, onSuccess: () -> Unit) {
        try {
            return currencyDao.createCurrencyItem(currency)

        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        try {
            return currencyDao.updateListFavoriteCurrency(currency.name, currency.is_favorite)

        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun updateListCurrency(currency: Currency, onSuccess: () -> Unit) {
        try {
            return currencyDao.updateListCurrency(currency.name, currency.value)

        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun getFavoriteCurrencyList(): List<Currency>? {
        try {
            return currencyDao.getFavoriteCurrencyList()
        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun getRUB(): Currency {
        try {
            return currencyDao.getRUB()
        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun getHistory(): List<History> {
        return currencyDao.getHistory()
    }

}