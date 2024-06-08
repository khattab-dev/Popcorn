package com.slayer.discovery.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.slayer.discovery.presentation.views.DefaultTabRow
import com.slayer.discovery.presentation.views.PosterCard
import com.slayer.discovery.presentation.views.SearchField
import com.slayer.discovery.presentation.views.TrendingRow
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.presentation.views.DefaultTab

@Composable
fun DiscoverScreen(vm: DiscoverViewModel = hiltViewModel<DiscoverViewModel>()) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val movies by vm.selectedMovies.collectAsState()

    LaunchedEffect(key1 = true) {
        vm.getNowPlayingMovies()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
    ) {
        item(span = { GridItemSpan(3) }) {
            SearchField()
        }

        item(span = { GridItemSpan(3) }) { Spacer(modifier = Modifier.height(16.dp)) }

        item(span = { GridItemSpan(3) }) { TrendingRow() }

        item(span = { GridItemSpan(3) }) { Spacer(modifier = Modifier.height(48.dp)) }

        item(span = { GridItemSpan(3) }) {
            DefaultTabRow(selectedTabIndex,vm.moviesMap.keys.toList()) {
                selectedTabIndex = it
                vm.setSelectedMovies(it)
            }
        }

        items(movies) {
            PosterCard(item = it, 150)
        }
    }
}