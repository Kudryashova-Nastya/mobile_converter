package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.domain.model.Currency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM CurrencyItem")
    fun getAll(): List<Currency>

    @Insert(entity = CurrencyItemDb::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun createCurrencyItem(currency: Currency)

    @Query("UPDATE CurrencyItem SET is_favorite = :is_favorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, is_favorite: Boolean)

    @Query("UPDATE CurrencyItem SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)

}