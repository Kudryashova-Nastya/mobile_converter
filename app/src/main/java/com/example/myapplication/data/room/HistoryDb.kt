package com.example.myapplication.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "History",
)
data class History (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name ="name_currency1") val name_currency1: String,
    @ColumnInfo(name ="value_currency1") val value_currency1: Double,
    @ColumnInfo(name ="name_currency2") val name_currency2: String,
    @ColumnInfo(name ="value_currency2") val value_currency2: Double,
    @ColumnInfo(name ="exchange_date") val exchange_date: String,
) : Serializable
