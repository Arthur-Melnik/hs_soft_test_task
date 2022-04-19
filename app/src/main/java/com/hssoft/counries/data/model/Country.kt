package com.hssoft.counries.data.model

data class Country(
    val code: String
    val name: String,
    val phonePrefix: String,
    val capital: String,
    val languages: List<Language>
)
