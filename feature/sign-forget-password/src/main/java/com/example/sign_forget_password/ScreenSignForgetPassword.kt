package com.example.sign_forget_password


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.core.ui.theme.ThemeBox

@Composable
fun ScreenSignForgetPassword() {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme
                    .background
            ), contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
                .padding(vertical = 40.dp),
            Arrangement.SpaceBetween
        ) {
            Column() {
                Column {
                    Text(
                        text = "Сброс пароля по email",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Напишите нам свой email для отправки кода",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                val email = remember {
                    mutableStateOf("")
                }
                Column {
                    Text(
                        text = "Электронная почта",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    BasicTextField(
                        value = email.value,
                        onValueChange = {
                            email.value = it
                        },
                        singleLine = true,
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        decorationBox = {
                            ThemeBox {
                                Box {
                                    if (email.value.isBlank()) {
                                        Text(
                                            text = "Электронная почта",
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
            }


            Button(
                onClick = {

                },
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraSmall)
                    .fillMaxWidth()
                    .height(60.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Авторизоваться",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,

                    )
            }

        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true,
    device = "spec:width=3000px,height=2340px,dpi=440,orientation=portrait",
)
@Composable
fun PreviewScreenSignForgetPassword() {
    AnimeViewAppTheme {
        ScreenSignForgetPassword()
    }
}