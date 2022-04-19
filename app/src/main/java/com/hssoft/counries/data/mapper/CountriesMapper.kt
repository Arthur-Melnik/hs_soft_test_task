package com.hssoft.counries.data.mapper

import com.hssoft.counries.CountriesQuery
import com.hssoft.counries.data.database.entity.CountryEntity
import com.hssoft.counries.data.database.entity.CountryWithLanguages
import com.hssoft.counries.data.database.entity.LanguageEntity
import com.hssoft.counries.data.model.Country
import com.hssoft.counries.data.model.Language

fun CountryWithLanguages.toUiModel() = Country(
    code = country.countryCode,
    name = country.name,
    phonePrefix = country.phonePrefix,
    capital = country.capital,
    languages = languages.map { language -> language.toUiModel() }
)

fun LanguageEntity.toUiModel() = Language(
    code = languageCode,
    name = name
)

fun CountriesQuery.Country.toEntity() = CountryWithLanguages(
    country = CountryEntity(
        countryCode = code,
        name = name,
        phonePrefix = phone,
        capital = capital ?: "",
    ),
    languages = languages.map { it.toEntity() }
)

fun CountriesQuery.Language.toEntity() = LanguageEntity(
    languageCode = code,
    name = name ?: ""
)