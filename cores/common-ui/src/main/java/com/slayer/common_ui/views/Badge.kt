package com.slayer.common_ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BadgeCard(
    text: String,
    textColor: Color,
) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 12.sp,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        )
    }
}