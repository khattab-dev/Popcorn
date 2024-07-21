package com.slayer.common_ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeadingTextOne(
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign
    )
}

@Composable
fun HeadingTextTwo(
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign
    )
}

@Composable
fun HeadingTextThree(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        textAlign = textAlign
    )
}

@Composable
fun HeadingTextFour(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.White
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign, color = color
    )
}