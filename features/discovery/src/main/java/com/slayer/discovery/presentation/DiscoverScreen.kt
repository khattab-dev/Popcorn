package com.slayer.discovery.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slayer.core.common_ui.theme.primaryLight
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.presentation.views.DefaultTabRow
import com.slayer.discovery.presentation.views.PosterCard
import com.slayer.discovery.presentation.views.SearchField
import com.slayer.discovery.presentation.views.TrendingRow

@Composable
fun DiscoverScreen(vm: DiscoverViewModel = hiltViewModel<DiscoverViewModel>()) {
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

        item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(16.dp)) }

        item(span = { GridItemSpan(2) }) {
            TrendingRow(trendingMovies, trendingMoviesRotatedStates)
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
            ) {
                rotatedStates[movie.id] = !rotatedStates.getOrElse(movie.id) { false }
            }
        }
    }
}
