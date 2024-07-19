package com.slayer.network.di

import com.slayer.common.Constants
import com.slayer.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient = HttpClient(OkHttp) {
        defaultRequest {
            url(Constants.BASE_URL)
            header(Constants.HEADER_TOKEN_KEY, Constants.HEADER_TOKEN_VALUE)
        }

        install(Logging) {
            logger = Logger.DEFAULT
        }

        install(ContentNegotiation) {
            json(Json{
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    @Provides
    @Singleton
    fun provideApiService(httpClient: HttpClient) : ApiService = ApiService(httpClient)
}