package com.example.myapplication.domain.repository

import android.util.Log
import com.example.myapplication.data.RoomCurrencyRepository
import com.example.myapplication.data_source.LocalDataSource
import com.example.myapplication.data_source.RemoteDataSource
import com.example.myapplication.domain.mapper.CurrencyDtoMapper
import com.example.myapplication.domain.model.Currencies

class Repository(
    private val localDataSource: RoomCurrencyRepository,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCurrencies(): Currencies? {
        // данные могут быть "свежими" 5 минут
        // если данные  "несвежие" то идём в сеть и сохраняем обновлённые данные
        // если "свежие" - возвращаем из бд
        // смотрим на свежесть
//        val localData = LocalDataSource.getCurrentData()
//        if (localData.isFresh) {
//            return localData
//        } else {
            try {
                val response = remoteDataSource.getCurrencies()
//                localDataSource.saveCurrencies(response)
                return CurrencyDtoMapper.mapResponseToDomainModel(response)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("MY_TAG", e.localizedMessage)
                return null
            }
//        }
    }
}