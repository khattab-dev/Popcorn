package com.slayer.discovery.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie

@Composable
fun PosterCard(
    height: Int,
    movie: Movie,
    onMovieClick: (Int) -> Unit
) {
    Column() {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .height(height.dp)
                .widthIn(max = 150.dp)
                .clickable {
                    onMovieClick(movie.id)
                },
            model = movie.poster,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.placeholder),
            fallback = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .width(150.dp), horizontalArrangement = Arrangement.SpaceBetween
            
        ) {
            Text(movie.releaseDate.take(4), fontSize = 10.sp, color = Color.Gray)

            Text(movie.lang, fontSize = 10.sp, color = Color.Gray)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    modifier = Modifier.size(10.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text("5.2", fontSize = 10.sp, color = Color.Gray)
            }
        }

        Text(
            modifier = Modifier.width(150.dp),
            textAlign = TextAlign.Justify,
            text = movie.title,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp
        )
    }
}