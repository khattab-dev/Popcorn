package com.slayer.discovery.domain.repositories

import com.slayer.discovery.domain.models.Movie
import com.slayer.network.Resource

interface DiscoverRepository {
    suspend fun getNowPlayingMovies() : Resource<List<Movie>>
    suspend fun getPopularMovies() : Resource<List<Movie>>
    suspend fun getTopRatedMovies() : Resource<List<Movie>>
    suspend fun getUpcomingMovies() : Resource<List<Movie>>
    suspend fun getTrendingMovies() : Resource<List<Movie>>
}