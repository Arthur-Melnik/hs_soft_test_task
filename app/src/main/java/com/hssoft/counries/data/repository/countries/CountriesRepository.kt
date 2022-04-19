package com.hssoft.counries.data.repository.countries

import com.hssoft.counries.data.model.Country
import com.hssoft.counries.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun loadCountries(fetchFromRemote: Boolean): Flow<Resource<List<Country>>>
}