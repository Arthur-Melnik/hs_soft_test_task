package com.hssoft.counries.data.remote.dto

import androidx.room.Entity

@Entity
data class LanguageDto(
    val languageCode: String,
    val name: String?
)
