package com.example.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


private val LightColorScheme = lightColorScheme(
    primary = Main_Orange,
    secondary = Gray_1,
    background = White,
    secondaryContainer = Border_Pink,
    primaryContainer = White,
    onBackground = Black,
)
private val DarkColorScheme = darkColorScheme(
    primary = Main_Orange,
    secondary = Gray_1,
    background = Black,
    secondaryContainer = Border_Pink,
    primaryContainer = White,
    onBackground = White,
)


@Composable
fun AnimeViewAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = typography(darkTheme),
        shapes = Shapes(
            extraSmall = RoundedCornerShape(10.dp),
            small = RoundedCornerShape(10.dp),
            medium = RoundedCornerShape(10.dp),
            large = RoundedCornerShape(10.dp),
            extraLarge = RoundedCornerShape(10.dp),
        ),
        content = content
    )
}