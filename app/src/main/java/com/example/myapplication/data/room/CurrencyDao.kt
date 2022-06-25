package com.example.myapplication.data.room

import androidx.room.*
import com.example.myapplication.data.room.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM CurrencyItem ORDER BY is_favorite DESC")
    suspend fun getAll(): List<Currency>

    @Insert(entity = Currency::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun createCurrencyItem(currency: Currency)

    @Query("UPDATE CurrencyItem SET is_favorite = :is_favorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, is_favorite: Boolean)

    @Query("UPDATE CurrencyItem SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)

    @Query("SELECT * FROM CurrencyItem WHERE is_favorite = 1")
    suspend fun getFavoriteCurrencyList(): List<Currency>?

    @Query("SELECT * FROM CurrencyItem WHERE name='RUB'")
    suspend fun getRUB(): Currency

}