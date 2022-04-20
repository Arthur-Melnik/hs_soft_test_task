package com.hssoft.counries.ui.countries.details

import com.hssoft.counries.data.model.Country

sealed class CountriesUiState
data class CountrySelected(val country: Country) : CountriesUiState()