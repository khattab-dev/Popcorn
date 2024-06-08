package com.slayer.discovery.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.slayer.core.common_ui.theme.primaryLight
import com.slayer.discovery.domain.models.Movie

@Composable
fun TrendingRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(Movie.getMovies().size) {
            Box {
                PosterCard(Movie.getMovies()[it], 200)

                Text(
                    text = "${it + 1}",
                    style = TextStyle(
                        fontSize = 96.sp, color = primaryLight, drawStyle = Stroke(
                            miter = 10f,
                            width = 5f,
                            join = StrokeJoin.Round
                        ),
                        shadow = Shadow(
                            color = Color(0xFF0296E5),
                            offset = Offset.Zero,  // Center the shadow around the text
                            blurRadius = 16f  // Adjust blur radius for glow effect
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(y = 48.dp)
                )

                Text(
                    text = "${it + 1}",
                    style = TextStyle(
                        fontSize = 96.sp, color = Color(0xFF242A32)
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(y = 48.dp)
                )
            }
        }
    }
}