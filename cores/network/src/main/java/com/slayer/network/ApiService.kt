package com.slayer.network

import com.slayer.common.Constants
import com.slayer.network.dto.movie_details.MovieDetailsResponse
import com.slayer.network.dto.movies.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(val client: HttpClient) {
    suspend inline fun <reified T : Any> getApiResponse(
        endpoint: String,
        path: Any = "",
    ): Resource<T> {
        return safeApiCall {
            client.get("$endpoint$path").body<T>()
        }
    }
}