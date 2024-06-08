package com.slayer.domain.usecases

import com.slayer.domain.repositories.DiscoverRepository

class GetNowPlayingMovies (private val repo: DiscoverRepository) {
    suspend operator fun invoke() = repo.getNowPlayingMovies()
}