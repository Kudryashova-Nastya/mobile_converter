package com.example.myapplication.data

import android.database.sqlite.SQLiteConstraintException
import com.example.myapplication.domain.model.Currency

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

//    override suspend fun createCurrency(newCurrency: CurrencyDb) {
//        try {
//            val entity = newCurrency
//            nodesDao.createCurrency(entity)
//        } catch (e: SQLiteConstraintException) {
//            val appException = RuntimeException()
//            appException.initCause(e)
//            throw appException
//        }
//    }

}