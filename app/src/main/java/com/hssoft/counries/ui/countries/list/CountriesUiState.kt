package com.hssoft.counries.ui.countries.list

import com.hssoft.counries.data.ui.model.Country

sealed class CountryListUiState

data class CountriesLoaded(val data: List<Country>) : CountryListUiState()
object CountriesLoading : CountryListUiState()
object CountriesLoadError : CountryListUiState()