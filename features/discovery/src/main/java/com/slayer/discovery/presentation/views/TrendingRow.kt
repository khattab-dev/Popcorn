package com.slayer.discovery.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.slayer.discovery.domain.models.Movie

@Composable
fun TrendingRow(
    trendingMovies: List<Movie>,
    onMovieClick: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(trendingMovies, key = { _, movie -> movie.id }) { index, movie ->
            Box {
                PosterCard(
                    height = 300,
                    movie = movie,
                    onMovieClick = {
                        onMovieClick(movie.id)
                    }
                )
            }
        }
    }
}