package com.slayer.movie_details.data.repositories

import com.slayer.common.Constants
import com.slayer.movie_details.data.mappers.toCast
import com.slayer.movie_details.data.mappers.toMovieDetails
import com.slayer.movie_details.domain.models.Cast
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.movie_details.domain.repositories.MovieDetailsRepository
import com.slayer.network.ApiService
import com.slayer.network.Resource
import com.slayer.network.dto.movie_credits.MovieCreditsResponse
import com.slayer.network.dto.movie_details.MovieDetailsResponse
import com.slayer.network.dto.movies.MoviesResponse
import javax.inject.Inject

class MovieDetailsRepoImpl @Inject constructor(
    private val api: ApiService
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(id : Int): Resource<MovieDetails> {
        return api.getApiResponse<MovieDetailsResponse>(Constants.ENDPOINT_MOVIE_DETAILS,id).map {
            it.toMovieDetails()
        }
    }

    override suspend fun getMovieCasts(id: Int): Resource<List<Cast>> {
        return api.getApiResponse<MovieCreditsResponse>(Constants.ENDPOINT_MOVIE_CASTS.replace("{movie_id}", id.toString())).map {
            it.toCast()
        }
    }
}