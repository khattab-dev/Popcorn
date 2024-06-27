package com.slayer.discovery.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.slayer.discovery.R
import com.slayer.discovery.domain.models.Movie

@Composable
fun PosterCard(item: Movie, height: Int) {
    Box (contentAlignment = Alignment.TopEnd){
        AsyncImage(
            modifier = Modifier
                .height(height.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = item.poster,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.placeholder),
            fallback = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
        )
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp).background(Color.Black.copy(alpha = 0.75f), shape = RoundedCornerShape(16.dp)).padding(vertical = 4.dp, horizontal = 8.dp)) {
            Icon(imageVector = Icons.Filled.Star, contentDescription = null, tint = Color.Yellow,modifier = Modifier.size(10.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "${item.rating}", style = TextStyle(fontSize = 10.sp, color = Color.Yellow, fontWeight = FontWeight.Bold))
        }
    }
}