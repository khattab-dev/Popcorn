package com.slayer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slayer.domain.models.Movie
import com.slayer.presentation.views.DefaultTabRow
import com.slayer.presentation.views.PosterCard
import com.slayer.presentation.views.SearchField
import com.slayer.presentation.views.TrendingRow

@Composable
fun DiscoverScreen() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var moviesTab by remember { mutableStateOf(Movie.getMovies()) }

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
            DefaultTabRow(selectedTabIndex) { movies , index ->
                moviesTab = movies
                selectedTabIndex = index
            }
        }

        items(moviesTab) {
            PosterCard(item = it, 150)
        }
    }
}