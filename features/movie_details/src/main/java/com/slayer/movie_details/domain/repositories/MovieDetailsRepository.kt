package com.slayer.movie_details.domain.repositories

import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.network.Resource

interface MovieDetailsRepository {
    suspend fun getMovieDetails(id : Int) : Resource<MovieDetails>
}