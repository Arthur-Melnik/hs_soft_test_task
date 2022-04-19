package com.hssoft.counries.data.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hssoft.counries.data.database.entity.CountryWithLanguages

interface CountriesDao {
    @Transaction
    @Query("SELECT * FROM CountryEntity")
    suspend fun getCountriesWithLanguages(): List<CountryWithLanguages>

    @Transaction
    @Query("SELECT * FROM CountryEntity WHERE countryCode =:countryCode")
    suspend fun getCountryWithLanguagesByCode(countryCode: String): CountryWithLanguages

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg countryWithLanguages: CountryWithLanguages)

    @Query("DROP TABLE CountryEntity")
    suspend fun deleteCountries()

    @Query("DROP TABLE LanguageEntity")
    suspend fun deleteLanguages()

    @Transaction
    suspend fun deleteAll() {
        deleteCountries()
        deleteLanguages()
    }
}