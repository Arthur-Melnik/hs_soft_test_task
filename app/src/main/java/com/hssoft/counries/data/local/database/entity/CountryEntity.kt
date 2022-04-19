package com.hssoft.counries.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey
    val countryCode: String,
    val name: String,
    val phonePrefix: String,
    val capital: String,
)
