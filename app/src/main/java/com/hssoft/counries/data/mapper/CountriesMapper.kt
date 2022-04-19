package com.hssoft.counries.data.mapper

import com.hssoft.counries.data.local.database.entity.CountryWithLanguages
import com.hssoft.counries.data.local.database.entity.LanguageEntity
import com.hssoft.counries.data.ui.model.Country
import com.hssoft.counries.data.ui.model.Language

fun CountryWithLanguages.fromEntity() = Country(
    name = country.name,
    phonePrefix = country.phonePrefix,
    capital = country.capital,
    languages = languages.map { language -> language.fromEntity() }
)

fun LanguageEntity.fromEntity() = Language(
    code = languageCode,
    name = name
)