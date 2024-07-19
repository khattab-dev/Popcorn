package com.slayer.movie_details.presentation

import RatingBar
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
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
    val cast by vm.cast.collectAsState()

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val imageHeight = screenHeight.value * 0.33

    val castScrollState = rememberScrollState()

    movieResult?.let { movie ->
        LazyColumn() {
            item {
                AsyncImage(
                    model = movie.backgroundImage,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier.height(imageHeight.dp)
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight(),

                    verticalArrangement = Arrangement.SpaceBetween, // Ensure space between the columns
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        AsyncImage(
                            model = movie.poster,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .width(125.dp)
                                .height(210.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .wrapContentSize(),
                        )


                        Column(modifier = Modifier.height(210.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column {
                                HeadingTextTwo(text = movie.title)

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${movie.releaseDate} • ${movie.movieLength}",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
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


                                RatingBar(
                                    rating = movie.rating.toFloat() / 2,
                                    starSize = 17.dp,
                                )
                            }

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                movie.genres.forEach { genre ->
                                    BadgeCard(
                                        text = genre,
                                        textColor = MaterialTheme.colorScheme.background
                                    )
                                }
                            }
                        }

                    }
                }
            }

            item {
                HorizontalDivider()
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    HeadingTextTwo(text = "Overview")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.overview,
                        style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Justify),
                        color = Color.Gray,
                    )
                }
            }

            item {
                HorizontalDivider()
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    HeadingTextTwo(text = "Actors")

                    Row(
                        modifier = Modifier
                            .horizontalScroll(castScrollState)
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        cast.forEach { actor ->
                            val placeHolder =
                                if (actor.isMale) R.drawable.male_avatar else R.drawable.female_avatar

                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.width(85.dp)
                            ) {
                                AsyncImage(
                                    model = actor.image,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(placeHolder),
                                    fallback = painterResource(placeHolder),
                                    error = painterResource(placeHolder),
                                    modifier = Modifier
                                        .size(85.dp)
                                        .clip(CircleShape)
                                        .fillMaxSize()
                                )

                                Text(
                                    text = actor.name,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    lineHeight = TextUnit(16f, TextUnitType.Sp)
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}