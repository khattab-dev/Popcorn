package com.slayer.movie_details.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MovieDetailsScreen(vm: MovieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()) {
    val movie by vm.movieDetails.collectAsState()

    movie?.let {
        Text(text = it.title)
    }
}