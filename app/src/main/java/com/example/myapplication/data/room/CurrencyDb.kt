package com.example.myapplication.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(
    tableName = "CurrencyInfo"
)
data class CurrencyInfoDb(
    @PrimaryKey(autoGenerate = true) val id: Long,
//    @ColumnInfo(name = "success") val success: Boolean,
//    @ColumnInfo(name = "timestamp") val timestamp: Long,
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

@Entity(
    tableName = "CurrencyItem",
//    foreignKeys = [
//        ForeignKey(
//            entity = CurrencyInfoDb::class,
//            parentColumns = ["id"],
//            childColumns = ["currencyInfo"],
//            onDelete = ForeignKey.CASCADE,
//            onUpdate = ForeignKey.CASCADE
//        )
//    ]
)
data class Currency(
//    @PrimaryKey(autoGenerate = true) val id: Long,
//    @ColumnInfo(name = "currencyInfo") val currencyInfo: Long,
    @PrimaryKey val name: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "is_favorite", defaultValue = "false") val is_favorite: Boolean
) : Serializable
