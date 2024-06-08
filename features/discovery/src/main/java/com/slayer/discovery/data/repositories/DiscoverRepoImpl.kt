package com.slayer.discovery.data.repositories

import com.slayer.common.Constants
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.domain.repositories.DiscoverRepository
import com.slayer.network.ApiService
import com.slayer.network.Resource
import javax.inject.Inject

class DiscoverRepoImpl @Inject constructor(
    private val api: ApiService
) : DiscoverRepository {
    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        return api.getTrendingMovies(Constants.ENDPOINT_NOW_PLAYING).map {
            it.movieResults.map { result ->
                Movie(
                    id = result.id,
                    title = result.title,
                    poster = "https://image.tmdb.org/t/p/w500/${result.posterPath}"
                )
            }
        }
    }
}