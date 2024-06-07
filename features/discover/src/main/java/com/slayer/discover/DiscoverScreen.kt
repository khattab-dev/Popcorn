package com.slayer.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.slayer.core.common_ui.models.Movie
import com.slayer.core.common_ui.theme.primaryLight
import com.slayer.discover.views.DefaultTab
import com.slayer.discover.views.DefaultTabRow
import com.slayer.discover.views.PosterCard
import com.slayer.discover.views.SearchField
import com.slayer.discover.views.TrendingRow

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