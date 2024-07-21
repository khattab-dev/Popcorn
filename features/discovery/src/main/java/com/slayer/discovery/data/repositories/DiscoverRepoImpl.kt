package com.slayer.discovery.data.repositories

import com.slayer.common.Constants
import com.slayer.discovery.data.mappers.toMovies
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.domain.repositories.DiscoverRepository
import com.slayer.network.ApiService
import com.slayer.network.Resource
import com.slayer.network.dto.movies.MoviesResponse
import javax.inject.Inject

class DiscoverRepoImpl @Inject constructor(
    private val api: ApiService
) : DiscoverRepository {
    override suspend fun getNowPlayingMovies(): Resource<List<Movie>> {
        return api.getApiResponse<MoviesResponse>(Constants.ENDPOINT_NOW_PLAYING).map {
            it.toMovies()
        }
    }

    override suspend fun getPopularMovies(): Resource<List<Movie>> {
        return api.getApiResponse<MoviesResponse>(Constants.ENDPOINT_POPULAR).map {
            it.toMovies()
        }
    }

    override suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return api.getApiResponse<MoviesResponse>(Constants.ENDPOINT_TOP_RATED).map {
            it.toMovies()
        }
    }

    override suspend fun getUpcomingMovies(): Resource<List<Movie>> {
        return api.getApiResponse<MoviesResponse>(Constants.ENDPOINT_UPCOMING).map {
            it.toMovies()
        }
    }

    override suspend fun getTrendingMovies(): Resource<List<Movie>> {
        return api.getApiResponse<MoviesResponse>(Constants.ENDPOINT_TRENDING).map {
            it.toMovies().take(10)
        }
    }
}