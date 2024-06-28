package com.slayer.discovery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slayer.common.Constants
import com.slayer.common.utils.printToLog
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.domain.usecases.*
import com.slayer.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    val selectedMovies: StateFlow<List<Movie>> = _selectedMovies.asStateFlow()

    private val _trendingMovies = MutableStateFlow(emptyList<Movie>())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies.asStateFlow()

    private val _searchValue = MutableStateFlow("")
    val searchValue: StateFlow<String> = _searchValue.asStateFlow()

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    private val moviesMap: MutableMap<String, List<Movie>> = mutableMapOf(
        Constants.NOW_PLAYING to emptyList(),
        Constants.POPULAR to emptyList(),
        Constants.TOP_RATED to emptyList(),
        Constants.UPCOMING to emptyList()
    )

    init {
        viewModelScope.launch {
            // Initialize trending movies and start collecting movies based on selected tab
            fetchTrendingMovies(null)
            collectMoviesByTab()
        }
    }

    /**
     * Fetches movies from the provided use case and stores them in the moviesMap under the given key.
     * If the movies are already fetched, it does nothing.
     */
    private suspend fun fetchAndStoreMovies(key: String, fetchMovies: suspend () -> Resource<List<Movie>>) {
        if (moviesMap[key].isNullOrEmpty()) {
            fetchMovies.invoke().onSuccess {
                moviesMap[key] = it
            }.onFailure {
                it.message?.printToLog()
            }
        }
    }

    /**
     * Updates the _selectedMovies flow with movies filtered by the search value.
     * Fetches movies if not already present in the moviesMap.
     */
    private suspend fun updateMovies(key: String, value: String) {
        fetchAndStoreMovies(key) {
            when (key) {
                Constants.NOW_PLAYING -> getNowPlayingMovies.invoke()
                Constants.POPULAR -> getPopularMovies.invoke()
                Constants.TOP_RATED -> getTopRatedMovies.invoke()
                Constants.UPCOMING -> getUpcomingMovies.invoke()
                else -> Resource.Failure(IllegalArgumentException("Unknown key"))
            }
        }

        _selectedMovies.value = moviesMap[key]?.filter { movie ->
            movie.title.contains(value, ignoreCase = true)
        } ?: emptyList()
    }

    /**
     * Fetches trending movies and updates the _trendingMovies flow.
     * Filters the movies if a search value is provided.
     */
    private suspend fun fetchTrendingMovies(searchValue: String?) {
        getTrendingMovies.invoke().onSuccess {
            _trendingMovies.value = if (!searchValue.isNullOrEmpty()) {
                it.filter { movie -> movie.title.contains(searchValue, ignoreCase = true) }
            } else {
                it
            }
        }.onFailure {
            it.message?.printToLog()
        }
    }

    /**
     * Updates the search value flow.
     */
    fun updateSearchValue(value: String) {
        _searchValue.value = value
    }

    /**
     * Updates the selected tab flow.
     */
    fun updateSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    /**
     * Collects movies based on the selected tab and search value.
     * Updates the _selectedMovies flow with the appropriate movies.
     */
    private fun collectMoviesByTab() = viewModelScope.launch {
        combine(searchValue, selectedTab) { searchValue, selectedTab ->
            Pair(searchValue, selectedTab)
        }.collectLatest { (searchValue, selectedTab) ->
            when (selectedTab) {
                0 -> updateMovies(Constants.NOW_PLAYING, searchValue)
                1 -> updateMovies(Constants.POPULAR, searchValue)
                2 -> updateMovies(Constants.TOP_RATED, searchValue)
                else -> updateMovies(Constants.UPCOMING, searchValue)
            }
        }
    }

    /**
     * Return the list of keys in the moviesMap.
     * To be used in the tab layout in the UI.
     */
    fun getMoviesKeys() : List<String> {
        return moviesMap.keys.toList()
    }

    val genresMap = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )
}
