package com.slayer.discovery.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slayer.common_ui.views.HeadingTextFour
import com.slayer.common_ui.views.HeadingTextTwo
import com.slayer.discovery.R
import com.slayer.discovery.presentation.views.Carousel
import com.slayer.discovery.presentation.views.PosterCard

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

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item(span = { GridItemSpan(3) }) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                HeadingTextTwo(text = stringResource(id = R.string.trending))
                Carousel(trendingMovies, 200)
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item(span = { GridItemSpan(3) }) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(id = com.slayer.common_ui.R.drawable.play_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary
                            ).padding(4.dp).size(16.dp)
                        )

                        HeadingTextFour(text = stringResource(R.string.now_playing))
                    }

                    HeadingTextFour(
                        text = stringResource(R.string.see_all),
                        modifier = Modifier.clickable {
                            // TODO :// NAVIGATE TO ALL MOVIES FILTERED
                        })
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(nowPlaying, key = { movie -> "now-playing${movie.id}" }) { movie ->
                        PosterCard(
                            height = 250,
                            movie = movie,
                            onMovieClick = onMovieClick
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item(span = { GridItemSpan(3) }) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(id = com.slayer.common_ui.R.drawable.play_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary
                            ).padding(4.dp).size(16.dp)
                        )

                        HeadingTextFour(text = stringResource(R.string.upcoming))
                    }

                    HeadingTextFour(
                        text = stringResource(R.string.see_all),
                        modifier = Modifier.clickable {
                            // TODO :// NAVIGATE TO ALL MOVIES FILTERED
                        })
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(upcoming, key = { movie -> "upcoming${movie.id}" }) { movie ->
                        PosterCard(
                            height = 250,
                            movie = movie,
                            onMovieClick = onMovieClick
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item(span = { GridItemSpan(3) }) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(id = com.slayer.common_ui.R.drawable.play_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary
                            ).padding(4.dp).size(16.dp)
                        )

                        HeadingTextFour(text = stringResource(R.string.popular))
                    }

                    HeadingTextFour(
                        text = stringResource(R.string.see_all),
                        modifier = Modifier.clickable {
                            // TODO :// NAVIGATE TO ALL MOVIES FILTERED
                        })
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(popular, key = { movie -> "popular${movie.id}" }) { movie ->
                        PosterCard(
                            height = 250,
                            movie = movie,
                            onMovieClick = onMovieClick
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item(span = { GridItemSpan(3) }) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            painter = painterResource(id = com.slayer.common_ui.R.drawable.play_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.background(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary
                            ).padding(4.dp).size(16.dp)
                        )

                        HeadingTextFour(text = stringResource(R.string.top_rated))
                    }

                    HeadingTextFour(
                        text = stringResource(R.string.see_all),
                        modifier = Modifier.clickable {
                            // TODO :// NAVIGATE TO ALL MOVIES FILTERED
                        })
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(topRated, key = { movie -> "top-rated${movie.id}" }) { movie ->
                        PosterCard(
                            height = 250,
                            movie = movie,
                            onMovieClick = onMovieClick
                        )
                    }
                }
            }
        }
    }
}
