package com.slayer.movie_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.slayer.common.utils.printToLog
import com.slayer.common_ui.Routes
import com.slayer.movie_details.domain.models.Cast
import com.slayer.movie_details.domain.models.MovieDetails
import com.slayer.movie_details.domain.usecases.GetCastUseCase
import com.slayer.movie_details.domain.usecases.GetMovieBackdropsUseCase
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
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getCastUseCase: GetCastUseCase,
    private val getMovieBackdropsUseCase: GetMovieBackdropsUseCase
) : ViewModel() {
    private val movieId = savedStateHandle.toRoute<Routes.MovieDetailsArgs>().id

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails = _movieDetails.asStateFlow()

    private val _cast = MutableStateFlow<List<Cast>>(emptyList())
    val cast = _cast.asStateFlow()

    private val _backdrops = MutableStateFlow<List<String>>(emptyList())
    val backdrops = _backdrops.asStateFlow()

    init {
        viewModelScope.launch {
            fetchMovieDetails()
            fetchMovieCast()
            fetchBackdrops()
        }
    }

    private suspend fun fetchMovieDetails() {
        getMovieDetailsUseCase.invoke(movieId).onSuccess {
            _movieDetails.value = it
        }.onFailure {
            it.message?.printToLog()
        }
    }

    private suspend fun fetchMovieCast() {
        getCastUseCase.invoke(movieId).onSuccess {
            _cast.value = it
        }.onFailure {
            it.message?.printToLog()
        }
    }

    private suspend fun fetchBackdrops() {
        getMovieBackdropsUseCase.invoke(movieId).onSuccess {
            _backdrops.value = it
        }.onFailure {
            it.message?.printToLog()
        }
    }
}