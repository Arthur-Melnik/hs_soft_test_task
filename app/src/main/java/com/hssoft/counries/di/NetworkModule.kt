package com.hssoft.counries.di

import com.apollographql.apollo3.ApolloClient
import com.hssoft.counries.data.utils.Constants
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {

    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constants.COUNTRIES_GRAPHQL_SERVER_URL)
            .build()
    }

}