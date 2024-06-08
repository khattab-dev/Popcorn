package com.slayer.discovery.presentation.views

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.slayer.discovery.domain.models.Movie

@Composable
fun PosterCard(item: Movie, height: Int) {
    AsyncImage(
        modifier = Modifier
            .height(height.dp)
            .clip(RoundedCornerShape(16.dp)),
        model = item.poster,
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}