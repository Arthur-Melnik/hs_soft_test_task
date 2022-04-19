package com.hssoft.counries.data.database

import androidx.room.Database
import com.hssoft.counries.data.database.dao.CountriesDao
import com.hssoft.counries.data.database.entity.CountryEntity
import com.hssoft.counries.data.database.entity.LanguageEntity

@Database(entities = [CountryEntity::class, LanguageEntity::class], version = 1)
abstract class AppDatabase {
    abstract fun countriesDao(): CountriesDao
}