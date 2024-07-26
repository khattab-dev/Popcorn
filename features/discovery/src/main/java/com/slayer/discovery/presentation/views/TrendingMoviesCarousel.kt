package com.slayer.discovery.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slayer.common_ui.views.HeadingTextTwo
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie

@Composable
fun TrendingMoviesCarousel(trendingMovies: List<Movie>, onMovieClick: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        HeadingTextTwo(text = stringResource(id = R.string.trending))
        Carousel(trendingMovies, 200, onMovieClick)
    }
}