package com.hssoft.counries.di.module

import com.apollographql.apollo3.ApolloClient
import com.hssoft.counries.utils.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constants.COUNTRIES_GRAPHQL_SERVER_URL)
            .build()
    }

}