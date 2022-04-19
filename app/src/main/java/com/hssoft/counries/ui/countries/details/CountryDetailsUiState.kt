package com.hssoft.counries.ui.countries.details

import com.hssoft.counries.data.model.Country

sealed class CountryDetailsUiState

data class CountriesLoaded(val data: Country) : CountryDetailsUiState()
object CountriesLoading : CountryDetailsUiState()
object CountriesLoadError : CountryDetailsUiState()