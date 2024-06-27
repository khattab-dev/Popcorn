package com.slayer.discovery.presentation

import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    val moviesMap: MutableMap<String, List<Movie>> = mutableMapOf(
        Constants.NOW_PLAYING to emptyList(),
        Constants.POPULAR to emptyList(),
        Constants.TOP_RATED to emptyList(),
        Constants.UPCOMING to emptyList()
    )

    init {
        viewModelScope.launch {
            getTrendingMovies(null)
            getMoviesByTab()
        }
    }

    private suspend fun getNowPlayingMovies() {
        if (moviesMap[Constants.NOW_PLAYING].isNullOrEmpty()) {
            getNowPlayingMovies.invoke().onSuccess {
                moviesMap[Constants.NOW_PLAYING] = it
            }.onFailure {
                Log.d("TAG", "getNowMovies: ${it.message}")
            }
        }

        _selectedMovies.value = moviesMap[Constants.NOW_PLAYING] ?: emptyList()
    }

    private suspend fun getPopularMovies() {
        if (moviesMap[Constants.POPULAR].isNullOrEmpty()) {
            getPopularMovies.invoke().onSuccess {
                moviesMap[Constants.POPULAR] = it
            }.onFailure {
                Log.d("TAG", "getPopularMovies: ${it.message}")
            }
        }

        _selectedMovies.value = moviesMap[Constants.POPULAR] ?: emptyList()
    }

    private suspend fun getTopRatedMovies() {
        if (moviesMap[Constants.TOP_RATED].isNullOrEmpty()) {
            getTopRatedMovies.invoke().onSuccess {
                moviesMap[Constants.TOP_RATED] = it
            }.onFailure {
                Log.d("TAG", "getTopMovies: ${it.message}")
            }
        }

        _selectedMovies.value = moviesMap[Constants.TOP_RATED] ?: emptyList()
    }

    private suspend fun getUpcomingMovies() {
        if (moviesMap[Constants.UPCOMING].isNullOrEmpty()) {
            getUpcomingMovies.invoke().onSuccess {
                moviesMap[Constants.UPCOMING] = it
            }.onFailure {
                Log.d("TAG", "getUpMovies: ${it.message}")
            }
        }

        _selectedMovies.value = moviesMap[Constants.UPCOMING] ?: emptyList()
    }

    private suspend fun getTrendingMovies(searchValue: String?) {
        getTrendingMovies.invoke().onSuccess {
            if (!searchValue.isNullOrEmpty()) {
                _trendingMovies.value = it.filter { movie ->
                    movie.title.contains(searchValue, ignoreCase = true)
                }
            } else {
                _trendingMovies.value = it
            }
        }.onFailure {
            Log.d("TAG", "getTrendingMovies: ${it.message}")
        }
    }

    fun setSearchValue(value: String) {
        _searchValue.value = value

        updateMovies()
    }

    private fun updateMovies() = viewModelScope.launch {
        getTrendingMovies(searchValue.value)
    }

    fun updateSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    private fun getMoviesByTab() = viewModelScope.launch {
        selectedTab.collectLatest {
            when (selectedTab.value) {
                0 -> {
                    getNowPlayingMovies()
                }

                1 -> {
                    getPopularMovies()
                }

                2 -> {
                    getTopRatedMovies()
                }

                else -> {
                    getUpcomingMovies()
                }
            }
        }
    }
}