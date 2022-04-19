package com.hssoft.counries.ui.countries.details

import com.hssoft.counries.data.ui.model.Country

sealed class CountryDetailsUiState

data class CountriesLoaded(val data: Country) : CountryDetailsUiState()
object CountriesLoading : CountryDetailsUiState()
object CountriesLoadError : CountryDetailsUiState()