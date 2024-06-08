package com.slayer.discovery.domain.usecases

import com.slayer.discovery.domain.repositories.DiscoverRepository
import javax.inject.Inject

class GetNowPlayingMovies @Inject constructor(private val repo: DiscoverRepository) {
    suspend operator fun invoke() = repo.getNowPlayingMovies()
}