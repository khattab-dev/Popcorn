package com.slayer.discover.views

import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DefaultTab(
    isSelected: Boolean,
    title: String,
    onTabClicked: () -> Unit
) {
    Tab(
        text = { Text(title) },
        selected = isSelected,
        onClick = {
            onTabClicked.invoke()
        }
    )
}