package com.slayer.discovery.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slayer.discovery.R
import com.slayer.discovery.presentation.views.MoviesRow
import com.slayer.discovery.presentation.views.TrendingMoviesCarousel

@Composable
fun DiscoverScreen(
    vm: DiscoverViewModel = hiltViewModel<DiscoverViewModel>(),
    onMovieClick: (Int) -> Unit
) {

    val nowPlaying by vm.nowPlaying.collectAsState()
    val popular by vm.popular.collectAsState()
    val upcoming by vm.upcoming.collectAsState()
    val topRated by vm.topRated.collectAsState()
    val trendingMovies by vm.trendingMovies.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            TrendingMoviesCarousel(trendingMovies, onMovieClick)
        }

        item {
            MoviesRow(
                nowPlaying,
                onMovieClick,
                stringResource(R.string.now_playing),
            )
        }

        item {
            MoviesRow(
                upcoming,
                onMovieClick,
                stringResource(R.string.upcoming),
            )
        }

        item {
            MoviesRow(
                popular,
                onMovieClick,
                stringResource(R.string.popular),
            )
        }

        item {
            MoviesRow(
                topRated,
                onMovieClick,
                stringResource(R.string.top_rated),
            )
        }
    }
}