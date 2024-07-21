package com.slayer.discovery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slayer.common.utils.printToLog
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.domain.usecases.GetNowPlayingMovies
import com.slayer.discovery.domain.usecases.GetPopularMovies
import com.slayer.discovery.domain.usecases.GetTopRatedMovies
import com.slayer.discovery.domain.usecases.GetTrendingMovies
import com.slayer.discovery.domain.usecases.GetUpcomingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getPopularMovies: GetPopularMovies,
    private val getTopRatedMovies: GetTopRatedMovies,
    private val getUpcomingMovies: GetUpcomingMovies,
    private val getTrendingMovies: GetTrendingMovies
) : ViewModel() {

    private val _nowPlaying = MutableStateFlow(emptyList<Movie>())
    val nowPlaying: StateFlow<List<Movie>> = _nowPlaying.asStateFlow()

    private val _upcoming = MutableStateFlow(emptyList<Movie>())
    val upcoming: StateFlow<List<Movie>> = _upcoming.asStateFlow()

    private val _topRated = MutableStateFlow(emptyList<Movie>())
    val topRated: StateFlow<List<Movie>> = _topRated.asStateFlow()

    private val _popular = MutableStateFlow(emptyList<Movie>())
    val popular: StateFlow<List<Movie>> = _popular.asStateFlow()

    private val _trendingMovies = MutableStateFlow(emptyList<Movie>())
    val trendingMovies = _trendingMovies.asStateFlow()

    init {
        viewModelScope.launch {
            fetchTrendingMovies()
            fetchDiscoverMovies()
        }
    }

    /**
     * Fetches movies from the provided use case and stores them in the moviesMap under the given key.
     * If the movies are already fetched, it does nothing.
     */
    private suspend fun fetchDiscoverMovies() {
        getNowPlayingMovies.invoke().onSuccess {
            _nowPlaying.value = it
        }.onFailure {
            it.message?.printToLog()
        }

        getUpcomingMovies.invoke().onSuccess {
            _upcoming.value = it
        }.onFailure {
            it.message?.printToLog()
        }

        getPopularMovies.invoke().onSuccess {
            _popular.value = it
        }.onFailure {
            it.message?.printToLog()
        }

        getTopRatedMovies.invoke().onSuccess {
            _topRated.value = it
        }.onFailure {
            it.message?.printToLog()
        }
    }


    /**
     * Fetches trending movies and updates the _trendingMovies flow.
     * Filters the movies if a search value is provided.
     */
    private suspend fun fetchTrendingMovies() {
        getTrendingMovies.invoke().onSuccess {
            _trendingMovies.value = it
        }.onFailure {
            it.message?.printToLog()
        }
    }
}
