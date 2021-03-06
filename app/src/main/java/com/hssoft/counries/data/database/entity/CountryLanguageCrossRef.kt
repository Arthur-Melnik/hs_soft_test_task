package com.hssoft.counries.data.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["countryCode", "languageCode"])
data class CountryLanguageCrossRef(
    val countryCode: String,
    val languageCode: String
)
