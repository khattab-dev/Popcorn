package com.slayer.movie_details.presentation

import RatingBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.slayer.common.utils.createGenresList
import com.slayer.common.utils.printToLog
import com.slayer.common_ui.views.BadgeCard
import com.slayer.common_ui.views.HeadingTextThree
import com.slayer.common_ui.views.HeadingTextTwo
import com.slayer.common_ui.views.IconBadge
import com.slayer.movie_details.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsScreen(vm: MovieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()) {
    val movieResult by vm.movieDetails.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val imageHeight = screenHeight.value * 0.33

    movieResult?.let { movie ->
        Column {
            Box(
                Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = movie.backgroundImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.height(imageHeight.dp)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(y = (imageHeight / 1.45).dp)
                        .padding(start = 16.dp)
                ) {
                    AsyncImage(
                        model = movie.poster,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .width(125.dp)
                            .height(207.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )

                    Column(modifier = Modifier.padding(start = 16.dp, top = 48.dp, end = 16.dp)) {
                        HeadingTextTwo(text = movie.title, minLines = 2)

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Column {
                                HeadingTextThree(text = movie.rating)
                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Ratings",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Light,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                RatingBar(
                                    rating = movie.rating.toFloat() / 2,
                                    starSize = 17.dp,
                                )

                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            movie.genres.forEach { genre ->
                                BadgeCard(text = genre, textColor = MaterialTheme.colorScheme.background)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height((imageHeight / 1.45f + 16).dp))
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))

            HeadingTextTwo(text = "Overview",modifier = Modifier.padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.overview, modifier = Modifier.padding(horizontal = 16.dp), style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Justify), color = Color.Gray,)

            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
        }
    }
}