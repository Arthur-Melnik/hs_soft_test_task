package com.hssoft.counries.data.repository.countries

import com.apollographql.apollo3.ApolloClient
import com.hssoft.counries.CountriesQuery
import com.hssoft.counries.data.database.dao.CountriesDao
import com.hssoft.counries.data.database.entity.CountryLanguageCrossRef
import com.hssoft.counries.data.database.entity.CountryWithLanguages
import com.hssoft.counries.data.mapper.toEntity
import com.hssoft.counries.data.mapper.toUiModel
import com.hssoft.counries.data.model.Country
import com.hssoft.counries.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient,
    private val countriesDao: CountriesDao
) : CountriesRepository {
    override suspend fun loadCountries(fetchFromRemote: Boolean): Flow<Resource<List<Country>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCountries = countriesDao.getCountriesWithLanguages()
            emit(Resource.Success(localCountries.map { it.toUiModel() }))

            val isDbEmpty = localCountries.isEmpty()
            val shouldLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val response = apolloClient.query(CountriesQuery()).execute()

            if (response.hasErrors()) {
                response.errors?.forEach { error ->
                    emit(Resource.Error(error.message))
                }
                emit(Resource.Loading(false))
                return@flow
            }

            val countryWithLanguageEntities =
                response.data?.countries?.map { it.toEntity() } ?: emptyList()

            updateCountriesDatabase(countryWithLanguageEntities)
            val countries = countriesDao.getCountriesWithLanguages().map { it.toUiModel() }
            emit(Resource.Success(countries))
            emit(Resource.Loading(false))
        }
    }

    private suspend fun updateCountriesDatabase(data: List<CountryWithLanguages>) {
        countriesDao.deleteAll()
        countriesDao.insertCountries(*data.map { it.country }.toTypedArray())
        countriesDao.insertLanguages(*data.flatMap { it.languages }.toTypedArray())

        data.forEach { countryWithLanguages ->
            countriesDao.assignLanguagesToCountries(
                *countryWithLanguages.languages.map { language ->
                    CountryLanguageCrossRef(
                        countryCode = countryWithLanguages.country.countryCode,
                        languageCode = language.languageCode
                    )
                }.toTypedArray()
            )
        }
    }
}