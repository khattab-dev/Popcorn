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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp),
    ) {
        item(span = { GridItemSpan(3) }) {
            SearchField(searchValue,vm::updateSearchValue)
        }

        item(span = { GridItemSpan(3) }) { Spacer(modifier = Modifier.height(16.dp)) }

        item(span = { GridItemSpan(3) }) { TrendingRow(trendingMovies) }

        item(span = { GridItemSpan(3) }) { Spacer(modifier = Modifier.height(48.dp)) }

        item(span = { GridItemSpan(3) }) {
            DefaultTabRow(selectedTab,vm.getMoviesKeys()) {
                vm.updateSelectedTab(it)
            }
        }

        items(movies) {
            PosterCard(item = it, 150)
        }
    }
}