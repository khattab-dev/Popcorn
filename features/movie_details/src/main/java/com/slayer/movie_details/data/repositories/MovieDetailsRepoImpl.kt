package com.slayer.movie_details.data.repositories

import com.slayer.movie_details.data.mappers.toMovieDetails
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.movie_details.domain.repositories.MovieDetailsRepository
import com.slayer.network.ApiService
import com.slayer.network.Resource
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(
    private val api: ApiService
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(id : Int): Resource<MovieDetails> {
        return api.getMovieDetails(id).map {
            it.toMovieDetails()
        }
    }
}