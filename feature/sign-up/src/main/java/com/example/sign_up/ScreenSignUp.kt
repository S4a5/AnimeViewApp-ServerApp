package com.example.sign_up


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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material.icons.outlined.KeyboardBackspace
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.core.ui.theme.ThemeBox

@Composable
fun ScreenSignUp(onClickBack: () -> Unit = {}, onClickRegistry: () -> Unit = {}) {
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
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onClickBack()
                    }
            )
            Column {
                Text(
                    text = "Регистрация",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Создайте учетную запись ",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            val email = remember {
                mutableStateOf("")
            }
            val password = remember {
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
                            if (password.value.isBlank()) {
                                Text(
                                    text = "Электронная почта",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            it()
                        }
                    },

                    )
            }
            Column {
                Text(
                    text = "Email",
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
                            if (password.value.isBlank()) {
                                Text(
                                    text = "Email",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            it()
                        }
                    },

                    )
            }
            Column {
                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                val isVisible = remember {
                    mutableStateOf(true)
                }
                BasicTextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                    visualTransformation = if (isVisible.value) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },

                    decorationBox = {

                        ThemeBox {
                            if (password.value.isBlank()) {
                                Text(
                                    text = "Пароль",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                            val icon = if (isVisible.value) {
                                Icons.Outlined.VisibilityOff
                            } else {
                                Icons.Outlined.Visibility
                            }

                            it()
                            Icon(
                                imageVector = icon,
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        isVisible.value = isVisible.value.not()
                                    }
                                    .size(25.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }

                    }
                )
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
                    text = "Регистрация",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.clickable {
                        onClickRegistry()
                    }
                )
            }
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "У вас уже есть учетная запись?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Войти",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true,
)
@Composable
fun PreviewScreenSignUp() {
    AnimeViewAppTheme {
        ScreenSignUp()
    }
}