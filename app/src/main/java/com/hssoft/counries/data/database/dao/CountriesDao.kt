package com.hssoft.counries.data.database.dao

import androidx.room.*
import com.hssoft.counries.data.database.entity.CountryEntity
import com.hssoft.counries.data.database.entity.CountryLanguageCrossRef
import com.hssoft.counries.data.database.entity.CountryWithLanguages
import com.hssoft.counries.data.database.entity.LanguageEntity

@Dao
interface CountriesDao {
    @Transaction
    @Query("SELECT * FROM CountryEntity")
    suspend fun getCountriesWithLanguages(): List<CountryWithLanguages>

    @Transaction
    @Query("SELECT * FROM CountryEntity WHERE countryCode =:countryCode")
    suspend fun getCountryWithLanguagesByCode(countryCode: String): CountryWithLanguages

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(vararg countryEntity: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLanguages(vararg language: LanguageEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun assignLanguagesToCountries(vararg crossRef: CountryLanguageCrossRef)

    @Query("DELETE FROM CountryEntity")
    suspend fun deleteCountries()

    @Query("DELETE FROM LanguageEntity")
    suspend fun deleteLanguages()

    suspend fun deleteAll() {
        deleteCountries()
        deleteLanguages()
    }
}