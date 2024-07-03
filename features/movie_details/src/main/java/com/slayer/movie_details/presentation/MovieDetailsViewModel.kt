package com.slayer.movie_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.slayer.common.utils.printToLog
import com.slayer.common_ui.Routes
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.movie_details.domain.usecases.GetMovieDetailsUseCase
import com.slayer.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val movieId = savedStateHandle.toRoute<Routes.MovieDetailsArgs>().id

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails = _movieDetails.asStateFlow()

    init {
        viewModelScope.launch {
            fetchMovieDetails()
        }
    }

    private suspend fun fetchMovieDetails() {
        getMovieDetailsUseCase.invoke(movieId).onSuccess {
            _movieDetails.value = it
        }.onFailure {
            it.message?.printToLog()
        }
    }
}