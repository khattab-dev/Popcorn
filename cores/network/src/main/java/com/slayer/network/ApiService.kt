package com.slayer.network

import com.slayer.common.Constants
import com.slayer.network.dto.movie_details.MovieDetailsResponse
import com.slayer.network.dto.movies.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client : HttpClient) {
    suspend fun getMovies(endpoint: String): Resource<MoviesResponse> {
        return safeApiCall {
            client.get(endpoint).body<MoviesResponse>()
        }
    }

    suspend fun getMovieDetails(id : Int): Resource<MovieDetailsResponse> {
        return safeApiCall {
            client.get("${Constants.ENDPOINT_MOVIE_DETAILS}$id").body<MovieDetailsResponse>()
        }
    }
}