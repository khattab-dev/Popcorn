package com.slayer.discover.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slayer.core.common_ui.models.Movie

@Composable
fun DefaultTabRow(
    selectedTabIndex: Int,
    onTabSelected : (List<Movie>, Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        modifier = Modifier.padding(bottom = 16.dp),
    ) {
        for ((key, value) in Movie.movieMap) {
            val index = Movie.movieMap.keys.indexOf(key)
            val isSelected = selectedTabIndex == index

            DefaultTab(isSelected, key) {
                onTabSelected.invoke(value,index)
            }
        }
    }
}