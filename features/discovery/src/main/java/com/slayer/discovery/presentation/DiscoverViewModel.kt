package com.slayer.discovery.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slayer.discovery.domain.usecases.GetNowPlayingMovies
import com.slayer.discovery.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getNowPlayingMovies: GetNowPlayingMovies
) : ViewModel() {
    private val _selectedMovies = MutableStateFlow(emptyList<Movie>())
    val selectedMovies = _selectedMovies.asStateFlow()

    val moviesMap: MutableMap<String, List<Movie>> = mutableMapOf(
        "Now Playing" to emptyList(),
        "Popular" to emptyList(),
        "Top Rated" to emptyList(),
        "Upcoming" to emptyList()
    )

    fun getNowPlayingMovies() = viewModelScope.launch {
        getNowPlayingMovies.invoke().onSuccess {
            moviesMap["Now Playing"] = it
        }.onFailure {

        }
    }

    fun setSelectedMovies(index : Int) {
        val moviesList = moviesMap.entries.toList()
        _selectedMovies.value = moviesList[index].value
    }
}