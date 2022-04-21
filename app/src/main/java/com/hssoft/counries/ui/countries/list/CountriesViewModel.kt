package com.hssoft.counries.ui.countries.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hssoft.counries.data.model.Country
import com.hssoft.counries.data.repository.countries.CountriesRepository
import com.hssoft.counries.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _state: MutableLiveData<CountriesUiState> = MutableLiveData()
    val state: LiveData<CountriesUiState> = _state

    fun loadCountries(fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            countriesRepository
                .loadCountries(fetchFromRemote)
                .collect(::handleCountriesResource)
        }
    }

    private fun handleCountriesResource(resource: Resource<List<Country>>) {
        when (resource) {
            is Resource.Loading -> {
                _state.value = CountriesLoading(resource.isLoading)
            }
            is Resource.Error -> {
                _state.value = CountriesLoadError(resource.message)
            }
            is Resource.Success -> {
                _state.value = CountriesLoaded(resource.data ?: emptyList())
            }
        }
    }

}