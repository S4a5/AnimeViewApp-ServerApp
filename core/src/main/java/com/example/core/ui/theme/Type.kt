package com.example.core.ui.theme

import androidx.compose.ui.text.font.FontFamily
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.R

// Set of Material typography styles to start with
val NexaFont = FontFamily(
    Font(R.font.helvetica_bold, FontWeight.Bold),
    Font(R.font.helvetica_regular, FontWeight.Medium),
    Font(R.font.helvetica_light, FontWeight.Light),


//    Font(R.font.helvetica_cyr_boldoblique,FontWeight.Bold),
//    Font(R.font.helvetica_cyr_oblique,FontWeight.Bold),

//    Font(R.font.helvetica_oblique,FontWeight.Bold),
//    Font(R.font.helvetica_boldoblique,FontWeight.Bold),
//    Font(R.font.helvetica_lightoblique,FontWeight.Normal),
)

private val DarkColorText = Color(0xFFFFFFFF)
private val LightColorText = Color(0xFF1E1E1E)

fun typography(darkTheme: Boolean): Typography {
    return Typography(
        titleLarge = TextStyle(
            fontSize = 24.sp,
            fontFamily = NexaFont,
            fontWeight = FontWeight.Bold,
        ),
        titleMedium = TextStyle(
            fontSize = 18.sp,
            fontFamily = NexaFont,
            fontWeight = FontWeight.Bold,
        ),
        titleSmall = TextStyle(
            fontSize = 14.sp,
            fontFamily = NexaFont,
            fontWeight = FontWeight.Bold,
        ),
        labelMedium = TextStyle(
            fontSize = 16.sp,
            fontFamily = NexaFont,
            fontWeight = FontWeight.Light,
        ),
        labelSmall = TextStyle(
            fontSize = 14.sp,
            fontFamily = NexaFont,
            fontWeight = FontWeight.Light,
        ),
    )
}
