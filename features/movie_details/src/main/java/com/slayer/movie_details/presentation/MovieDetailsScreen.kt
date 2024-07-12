package com.slayer.movie_details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.slayer.common_ui.views.IconBadge

@Composable
fun MovieDetailsScreen(vm: MovieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()) {
    val movie by vm.movieDetails.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val imageHeight = screenHeight.value * 0.33

    movie?.let { movie ->
        Column {
            
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(imageHeight.dp)
                ) {
                    AsyncImage(
                        model = movie.backgroundImage,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()

                    )

                    Box(Modifier.padding(8.dp).fillMaxSize()) {
                        IconBadge(
                            text = "${movie.rating}",
                            iconVector = Icons.Filled.Star,
                            alignment = Alignment.BottomEnd,
                            color = Color.Yellow,
                        )
                    }
                }
            
        }
    }
}