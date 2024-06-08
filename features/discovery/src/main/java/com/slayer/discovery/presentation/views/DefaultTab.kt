package com.slayer.discovery.presentation.views

import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DefaultTab(
    title: String,
    isSelected : Boolean,
    onTabClicked: () -> Unit,
) {
    Tab(
        text = { Text(title) },
        selected = isSelected,
        onClick = {
            onTabClicked.invoke()
        }
    )
}