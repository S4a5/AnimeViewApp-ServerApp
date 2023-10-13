package com.s4a4.core.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ThemeBox(fillMaxWidth: Float = 1f, box: @Composable () -> Unit) = Row(
    modifier = Modifier
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.small
        )
        .fillMaxWidth(fillMaxWidth)
        .height(50.dp)
        .background(
            color = MaterialTheme.colorScheme.onSurface,
            shape = MaterialTheme.shapes.small
        )
        .padding(start = 16.dp, end = 16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    box()
}