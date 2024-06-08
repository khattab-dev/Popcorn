package com.slayer.discovery.domain.usecases

import com.slayer.discovery.domain.repositories.DiscoverRepository
import javax.inject.Inject

class GetTrendingMovies @Inject constructor(private val repo: DiscoverRepository) {
    suspend operator fun invoke() = repo.getTrendingMovies()
}