package com.example.myapplication.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "Currency"
)
data class CurrencyDb(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "success") val success: Boolean,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "base") val base: String,
    @ColumnInfo(name = "date") val date: String
//    @ColumnInfo(name = "rates") val rates: Map<String, Double>
) : Parcelable {

//    companion object {
//        fun fromAddNewNode(addNewNode: AddNewNode): NodeDbEntity = NodeDbEntity(
//            id = 0,
//            value = addNewNode.value
//        )
//    }
}
