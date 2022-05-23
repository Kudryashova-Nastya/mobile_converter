package com.example.myapplication.domain.model

import java.util.*

data class Currencies(
    val date: String,
    val base: String,
    val rates: List<Currency>
)

data class Currency(
//    val id: Int,
    val name: String,
    val value: Double,
    val is_favorite: Boolean = false // сортировка в репозитории
)