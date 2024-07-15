package com.slayer.discovery.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slayer.core.common_ui.theme.primaryLight
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.presentation.views.DefaultTabRow
import com.slayer.discovery.presentation.views.PosterCard
import com.slayer.discovery.presentation.views.SearchField
import com.slayer.discovery.presentation.views.TrendingRow

@Composable
fun DiscoverScreen(vm: DiscoverViewModel = hiltViewModel<DiscoverViewModel>(),onMovieClick: (Int) -> Unit) {
    val selectedTab by vm.selectedTab.collectAsState()

    val movies by vm.selectedMovies.collectAsState()
    val trendingMovies by vm.trendingMovies.collectAsState()
    val searchValue by vm.searchValue.collectAsState()

    val rotatedStates = remember { mutableStateMapOf<Int, Boolean>() }
    val trendingMoviesRotatedStates = remember { mutableStateMapOf<Int, Boolean>() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
    ) {
        item(span = { GridItemSpan(2) }) {
            SearchField(searchValue, vm::updateSearchValue)
        }

        item(span = { GridItemSpan(2) }) {
            Row(modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF2196F3), // Blue
                                    Color(0xFF0D47A1)  // Dark Blue
                                ),
                                start = Offset(0f, 0f),
                                end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                            ),
                            shape = RoundedCornerShape(16.dp),
                        ).weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Movies", style = TextStyle(fontWeight = FontWeight.Bold))
                    }
                }


                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFF5722), // Orange
                                    Color(0xFFFFC107)
                                ),
                                start = Offset(0f, 0f),
                                end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                            ),
                            shape = RoundedCornerShape(16.dp),
                        ).weight(1f)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Tv Shows", style = TextStyle(fontWeight = FontWeight.Bold))
                    }
                }
            }
        }

        item(span = { GridItemSpan(2) }) {
            Text(
                stringResource(R.string.trending),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )
        }

        item(span = { GridItemSpan(2) }) {
            TrendingRow(trendingMovies, trendingMoviesRotatedStates, onMovieClick = onMovieClick)
        }

        item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(48.dp)) }

        item(span = { GridItemSpan(2) }) {

            DefaultTabRow(selectedTab, vm.getMoviesKeys()) {
                vm.updateSelectedTab(it)
            }
        }

        itemsIndexed(movies, key = { _, movie -> movie.id }) { index, movie ->
            PosterCard(
                movie = movie,
                250,
                isRotated = rotatedStates.getOrElse(movie.id) { false },
                onInfoClick = {
                    rotatedStates[movie.id] = !rotatedStates.getOrElse(movie.id) { false }
                },
                onMovieClick = onMovieClick
            )
        }
    }
}
