package com.hssoft.counries.data.ui.model

data class Country(
    val name: String,
    val phonePrefix: String,
    val capital: String,
    val languages: List<Language>
)
