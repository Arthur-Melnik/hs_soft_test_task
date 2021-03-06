package com.hssoft.counries.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LanguageEntity(
    @PrimaryKey
    val languageCode: String,
    val name: String
)
