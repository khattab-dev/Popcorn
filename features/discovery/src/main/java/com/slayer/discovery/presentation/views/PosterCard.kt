package com.slayer.discovery.presentation.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PosterCard(
    item: Movie,
    height: Int,
    isRotated: Boolean,
    genres: List<String>,
    onInfoBtnClick: () -> Unit
) {

    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!isRotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (isRotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .height(height.dp)
            .widthIn(max = 150.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clip(RoundedCornerShape(16.dp))
    ) {
        if (!isRotated) {
            AsyncImage(
                modifier = Modifier.graphicsLayer {
                    alpha = animateFront
                }.fillMaxSize(),
                model = item.poster,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                fallback = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
            )

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = animateFront
                    }
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                IconBadge(
                    text = "${item.rating}",
                    iconVector = Icons.Filled.Star,
                    alignment = Alignment.TopEnd,
                    color = Color.Yellow
                )

                IconBadge(
                    text = item.lang,
                    iconRes = R.drawable.baseline_language_24,
                    alignment = Alignment.BottomEnd,
                    color = Color.White
                )

                InfoButton(onInfoBtnClick)
            }
        } else {
            AsyncImage(
                modifier = Modifier.graphicsLayer {
                    alpha = animateBack
                    rotationY = rotation
                },
                model = item.poster,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                fallback = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
            )

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        alpha = animateBack
                        rotationY = rotation
                    }
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.75f))
                    .padding(8.dp)
            ) {

                IconBadge(
                    text = "${item.rating}",
                    iconVector = Icons.Filled.Star,
                    alignment = Alignment.TopEnd,
                    color = Color.Yellow
                )

                IconBadge(
                    text = item.releaseDate,
                    iconVector = Icons.Filled.DateRange,
                    alignment = Alignment.BottomEnd,
                    color = Color.White
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    InfoButton(onInfoBtnClick)

                    Spacer(modifier = Modifier.height(32.dp))

                    HeadingTextThree(
                        text = item.title,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        genres.forEach { genre ->
                            BadgeCard(text = genre, textColor = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoButton(onInfoBtnClick: () -> Unit) {
    IconButton(
        onClick = { onInfoBtnClick.invoke() },
        modifier = Modifier
            .size(20.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}