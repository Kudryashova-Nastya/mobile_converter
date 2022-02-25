package com.example.myapplication.data


import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData

class RoomCurrencyRepository(
    private val nodesDao: CurrencyDao
)  : CurrencyRepository {

    override suspend fun getAll(): LiveData<List<CurrencyDb?>> {
        return nodesDao.getAll()
    }

    override suspend fun createCurrency(newCurrency: CurrencyDb) {
        try {
            val entity = newCurrency
            nodesDao.createCurrency(entity)
        } catch (e: SQLiteConstraintException) {
            val appException = RuntimeException()
            appException.initCause(e)
            throw appException
        }
    }

}