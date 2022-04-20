package com.hssoft.counries.ui.countries.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hssoft.counries.data.model.Country

class CountryDetailsViewModel : ViewModel() {

    private val _state: MutableLiveData<CountriesUiState> = MutableLiveData()
    val state: LiveData<CountriesUiState> = _state

    fun selectCountry(country: Country) {
        _state.postValue(CountrySelected(country))
    }
}