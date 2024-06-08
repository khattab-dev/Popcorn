package com.slayer.domain.repositories

import com.slayer.domain.models.Movie

interface DiscoverRepository {
    suspend fun getNowPlayingMovies() : Any
}