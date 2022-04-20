package com.hssoft.counries.ui.countries.list

import com.hssoft.counries.data.model.Country

sealed class CountriesUiState
data class CountriesLoaded(val data: List<Country>) : CountriesUiState()
data class CountriesLoading(val isLoading: Boolean) : CountriesUiState()
data class CountriesLoadError(val message: String?) : CountriesUiState()