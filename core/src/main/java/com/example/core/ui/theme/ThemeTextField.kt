package com.example.core.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ThemeBox(box: @Composable () -> Unit) = Column{
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFDDD8D8),
                shape = MaterialTheme.shapes.small
            )
            .width(380.dp)
            .height(50.dp)
            .background(
                color = Color(0xFFFAFAFA),
                shape = MaterialTheme.shapes.small
            )
            .align(Alignment.CenterHorizontally)
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        box()
    }
}