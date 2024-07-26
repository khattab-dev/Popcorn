package com.slayer.discovery.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.slayer.common_ui.R
import com.slayer.common_ui.views.HeadingTextFour
import com.slayer.discovery.domain.models.Movie

@Composable
fun MoviesRow(
    nowPlaying: List<Movie>,
    onMovieClick: (Int) -> Unit,
    headerText: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.play_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                        .padding(4.dp)
                        .size(16.dp)
                )

                HeadingTextFour(text = headerText)
            }

            HeadingTextFour(
                text = stringResource(com.slayer.discovery.R.string.see_all),
                modifier = Modifier.clickable {
                    // TODO :// NAVIGATE TO ALL MOVIES FILTERED
                })
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(nowPlaying, key = { movie -> headerText + movie.id }) { movie ->
                PosterCard(
                    height = 250,
                    movie = movie,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}