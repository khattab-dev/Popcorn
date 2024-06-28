package com.slayer.discovery.presentation.views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
private fun BoxScope.IconBadge(
    text: String,
    color: Color,
    alignment: Alignment,
    badgeIcon: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .align(alignment)
            .background(
                Color.Black.copy(alpha = 0.9f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        badgeIcon()

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = TextStyle(
                fontSize = 10.sp,
                color = color,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun BoxScope.IconBadge(
    text: String,
    color: Color,
    alignment: Alignment,
    @DrawableRes iconRes: Int
) {
    IconBadge(text = text, color = color, alignment = alignment) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(10.dp)
        )
    }
}

@Composable
fun BoxScope.IconBadge(text: String, color: Color, alignment: Alignment, iconVector: ImageVector) {
    IconBadge(text = text, color = color, alignment = alignment) {
        Icon(
            imageVector = iconVector,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(10.dp)
        )
    }
}



