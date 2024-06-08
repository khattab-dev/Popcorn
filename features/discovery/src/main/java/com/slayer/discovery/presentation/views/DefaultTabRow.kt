package com.slayer.discovery.presentation.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slayer.discovery.domain.models.Movie
import com.slayer.discovery.domain.models.Movie.Companion.movieMap

@Composable
fun DefaultTabRow(
    selectedTabIndex: Int,
    tabs : List<String>,
    onTabSelected : (Int) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        modifier = Modifier.padding(bottom = 16.dp),
    ) {
        for (tab in tabs) {
            val tabIndex = tabs.indexOf(tab)

            DefaultTab(title = tab,selectedTabIndex == tabIndex) {
                onTabSelected.invoke(tabIndex)
            }
        }
    }
}