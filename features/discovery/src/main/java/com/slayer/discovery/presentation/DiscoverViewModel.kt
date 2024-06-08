package com.slayer.discovery.presentation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slayer.common.Constants
import com.slayer.discovery.domain.usecases.GetNowPlayingMovies
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.domain.usecases.GetPopularMovies
import com.slayer.discovery.domain.usecases.GetTopRatedMovies
import com.slayer.discovery.domain.usecases.GetTrendingMovies
import com.slayer.discovery.domain.usecases.GetUpcomingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val _selectedMovies = MutableStateFlow(emptyList<Movie>())
    val selectedMovies = _selectedMovies.asStateFlow()

    private val _trendingMovies = MutableStateFlow(emptyList<Movie>())
    val trendingMovies = _trendingMovies.asStateFlow()

    val moviesMap: MutableMap<String, List<Movie>> = mutableMapOf(
        Constants.NOW_PLAYING to emptyList(),
        Constants.POPULAR to emptyList(),
        Constants.TOP_RATED to emptyList(),
        Constants.UPCOMING to emptyList()
    )

    init {
        viewModelScope.launch {
            getTrendingMovies()
            getNowPlayingMovies()
            getPopularMovies()
            getTopRatedMovies()
            getUpcomingMovies()
        }
    }

    private suspend fun getNowPlayingMovies() {
        getNowPlayingMovies.invoke().onSuccess {
            if (moviesMap[Constants.NOW_PLAYING].isNullOrEmpty()) {
                moviesMap[Constants.NOW_PLAYING] = it

                _selectedMovies.value = it
            } else {
                moviesMap[Constants.NOW_PLAYING] = it
            }
        }.onFailure {
            Log.d("TAG", "getNowMovies: ${it.message}")
        }
    }

    private suspend fun getPopularMovies() {
        getPopularMovies.invoke().onSuccess {
            moviesMap[Constants.POPULAR] = it
        }.onFailure {
            Log.d("TAG", "getPopularMovies: ${it.message}")
        }
    }

    private suspend fun getTopRatedMovies() {
        getTopRatedMovies.invoke().onSuccess {
            moviesMap[Constants.TOP_RATED] = it
        }.onFailure {
            Log.d("TAG", "getTopMovies: ${it.message}")
        }
    }

    private suspend fun getUpcomingMovies() {
        getUpcomingMovies.invoke().onSuccess {
            moviesMap[Constants.UPCOMING] = it
        }.onFailure {
            Log.d("TAG", "getUpMovies: ${it.message}")
        }
    }

    private suspend fun getTrendingMovies() {
        getTrendingMovies.invoke().onSuccess {
            _trendingMovies.value = it
        }.onFailure {
            Log.d("TAG", "getUpMovies: ${it.message}")
        }
    }

    fun setSelectedMovies(index: Int) {
        val moviesList = moviesMap.entries.toList()
        _selectedMovies.value = moviesList[index].value
    }
}