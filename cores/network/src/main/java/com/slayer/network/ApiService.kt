package com.slayer.network

import com.slayer.network.dto.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client : HttpClient) {
    suspend fun getMovies(endpoint: String): Resource<MoviesResponse> {
        return safeApiCall {
            client.get(endpoint).body<MoviesResponse>()
        }
    }
}