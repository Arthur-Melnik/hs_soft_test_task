package com.hssoft.counries.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.hssoft.counries.data.database.AppDatabase
import com.hssoft.counries.data.database.dao.CountriesDao
import com.hssoft.counries.data.repository.countries.CountriesRepository
import com.hssoft.counries.data.repository.countries.CountriesRepositoryImpl
import com.hssoft.counries.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, Constants.DATABASE_NAME
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