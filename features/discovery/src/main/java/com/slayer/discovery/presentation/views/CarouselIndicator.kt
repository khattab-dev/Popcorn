package com.slayer.discovery.presentation.views

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.slayer.discovery.domain.models.Movie
import kotlin.math.max

@Composable
fun CarouselIndicator(movies: List<Movie>, pagerState: PagerState) {
    val indicatorScrollState = rememberLazyListState()
    val selectedColor = MaterialTheme.colorScheme.primary

    LaunchedEffect(key1 = pagerState.currentPage) {
        val visibleItems = indicatorScrollState.layoutInfo.visibleItemsInfo

        if (visibleItems.isNotEmpty()) {
            val currentPage = pagerState.currentPage
            val lastVisibleIndex = visibleItems.last().index
            val firstVisibleItemIndex = indicatorScrollState.firstVisibleItemIndex

            when {
                currentPage > lastVisibleIndex - 1 -> {
                    // Scroll to the end of the list
                    indicatorScrollState.animateScrollToItem(currentPage - visibleItems.size + 2)
                }
                currentPage <= firstVisibleItemIndex + 1 -> {
                    // Scroll to the start of the list
                    indicatorScrollState.animateScrollToItem(max(currentPage - 1, 0))
                }
            }
        }
    }

    // Constants for item sizes and paddings
    val itemSizeDp = 10.dp
    val smallItemSizeDp = 6.dp
    val itemPaddingDp = 8.dp
    val rowWidth = itemSizeDp * 3 + smallItemSizeDp * 2 + itemPaddingDp * 10

    LazyRow(
        userScrollEnabled = false,
        state = indicatorScrollState,
        modifier = Modifier
            .height(50.dp)
            .width(rowWidth),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(movies.size) { iteration ->
            val color = if (pagerState.currentPage == iteration) selectedColor else Color.LightGray

            item(key = "item$iteration") {
                val currentPage = pagerState.currentPage
                val firstVisibleIndex by remember { derivedStateOf { indicatorScrollState.firstVisibleItemIndex } }
                val lastVisibleIndex = indicatorScrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                val size by animateDpAsState(
                    targetValue = when (iteration) {
                        currentPage -> {
                            itemSizeDp
                        }
                        in firstVisibleIndex + 1 until lastVisibleIndex -> {
                            itemSizeDp
                        }
                        else -> {
                            smallItemSizeDp
                        }
                    }
                )

                IndicatorBox(itemPaddingDp, color, size)
            }
        }
    }
}

@Composable
private fun IndicatorBox(
    itemPaddingDp: Dp,
    color: Color,
    size: Dp
) {
    Box(
        modifier = Modifier
            .padding(itemPaddingDp)
            .shadow(2.dp, CircleShape)
            .background(color, CircleShape)
            .size(size)
    )
}