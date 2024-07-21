package com.slayer.discovery.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.slayer.common.utils.createGenresList
import com.slayer.common_ui.views.HeadingTextFour
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Carousel(items: List<Movie>, height: Int) {

    val pagerState = rememberPagerState(
        pageCount = { items.size },
        initialPage = 0
    )

    HorizontalPager(
        state = pagerState,
        pageSpacing = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        val movie = items[page]

        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .height(height.dp)
                    .clickable {

                    }
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 0f,
                            endY = height.toFloat()  // Adjust as needed
                        )
                    ),
                model = movie.backdrop,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                fallback = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        shape = RoundedCornerShape(8.dp),
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.5f)
                            ),
                            startY = 0f,
                            endY = height.toFloat()
                        )
                    )
            )

            val maxWidth = LocalConfiguration.current.screenWidthDp - 32

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                HeadingTextFour(
                    text = movie.title.uppercase(), modifier = Modifier
                        .width((maxWidth / 1.75).dp)
                )

                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    createGenresList(movie.genresIds).forEach {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}