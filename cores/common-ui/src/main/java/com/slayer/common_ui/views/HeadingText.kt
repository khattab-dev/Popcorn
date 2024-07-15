package com.slayer.common_ui.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun HeadingTextOne(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
        )
    )
}

@Composable
fun HeadingTextTwo(
    minLines : Int = 1,
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        minLines = minLines,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
        )
    )
}

@Composable
fun HeadingTextThree(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
        )
    )
}

@Composable
fun HeadingTextFour(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
        )
    )
}