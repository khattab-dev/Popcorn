package com.slayer.discovery.presentation.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.skydoves.cloudy.Cloudy
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie

@Composable
fun PosterCard(item: Movie, height: Int) {
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .height(height.dp)
            .clip(RoundedCornerShape(16.dp))
            .widthIn(max = 150.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        rotated = !rotated
                    }
                )
            }
    ) {
        if (!rotated) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = animateFront
                    },
                model = item.poster,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                fallback = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.TopEnd)
                    .background(Color.Black.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp))
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .graphicsLayer {
                        alpha = animateFront
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${item.rating}",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.Yellow,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.BottomEnd)
                    .background(Color.Black.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp))
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .graphicsLayer {
                        alpha = animateFront
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_language_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = item.lang,
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        } else {
            Box {
                Cloudy(radius = 16) {
                    AsyncImage(
                        model = item.poster,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        fallback = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.placeholder),
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .graphicsLayer {
                            alpha = animateBack
                            rotationY = rotation
                        },// Adjust the alpha value as needed
                )

                Text(
                    text = item.desc,
                    style = TextStyle(
                        fontSize = 10.sp,
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                        .graphicsLayer {
                            alpha = animateBack
                            rotationY = rotation
                        },
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}