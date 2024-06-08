package com.slayer.discovery.domain.repositories

import com.slayer.discovery.domain.models.Movie
import com.slayer.network.Resource

interface DiscoverRepository {
    suspend fun getNowPlayingMovies() : Resource<List<Movie>>
}