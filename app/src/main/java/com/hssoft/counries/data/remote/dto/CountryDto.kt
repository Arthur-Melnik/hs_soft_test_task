package com.hssoft.counries.data.remote.dto

data class CountryDto(
    val countryCode: String,
    val name: String?,
    val phonePrefix: String?,
    val capital: String?,
    val languages: List<LanguageDto> = emptyList()
)
