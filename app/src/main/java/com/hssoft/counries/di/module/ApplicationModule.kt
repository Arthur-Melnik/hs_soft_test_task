package com.hssoft.counries.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.hssoft.counries.data.database.AppDatabase
import com.hssoft.counries.data.database.dao.CountriesDao
import com.hssoft.counries.data.repository.countries.CountriesRepository
import com.hssoft.counries.data.repository.countries.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()
    }

    @Provides
    fun provideCountriesDao(database: AppDatabase): CountriesDao {
        return database.countriesDao()
    }

    @Provides
    fun provideCountriesRepository(
        apolloClient: ApolloClient,
        countriesDao: CountriesDao
    ): CountriesRepository {
        return CountriesRepositoryImpl(apolloClient, countriesDao)
    }

}