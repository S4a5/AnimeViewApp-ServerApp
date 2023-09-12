package com.example.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.core.ui.theme.ThemeBox

@Composable
fun ScreenHome() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(modifier = Modifier.fillMaxWidth(0.9f)) {
            Search()
        }
    }

}

@Composable
fun Search() {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Column {
            val email = remember {
                mutableStateOf("")
            }
            BasicTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                decorationBox = {
                    ThemeBox(0.9f) {
                        Box {
                            if (email.value.isBlank()) {
                                Text(
                                    text = "Поиск",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            it()
                        }
                    }
                },
            )
        }
        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "")
    }
}

@Composable
@Preview
fun PreviewScreenHome() {
    AnimeViewAppTheme {
        ScreenHome()
    }
}