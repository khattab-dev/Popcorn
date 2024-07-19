package com.slayer.movie_details.domain.repositories

import com.slayer.movie_details.domain.models.Cast
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.network.Resource

interface MovieDetailsRepository {
    suspend fun getMovieDetails(id : Int) : Resource<MovieDetails>
    suspend fun getMovieCasts(id : Int) : Resource<List<Cast>>
    suspend fun getMovieBackdrops(id : Int) : Resource<List<String>>
}