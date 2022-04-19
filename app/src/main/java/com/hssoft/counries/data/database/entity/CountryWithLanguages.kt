package com.hssoft.counries.data.database.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CountryWithLanguages(
    @Embedded val country: CountryEntity,
    @Relation(
        parentColumn = "countryCode",
        entityColumn = "languageCode",
        associateBy = Junction(CountryLanguageCrossRef::class)
    )
    val languages: List<LanguageEntity>
)