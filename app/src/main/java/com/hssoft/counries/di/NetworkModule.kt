package com.hssoft.counries.di

import com.apollographql.apollo3.ApolloClient
import com.hssoft.counries.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constants.COUNTRIES_GRAPHQL_SERVER_URL)
            .build()
    }

}