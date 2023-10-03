package com.example.sing_in


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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.core.ui.theme.ThemeBox

@Composable
fun ScreenSignIn() {
    val viewModel: ViewModelSingIn = hiltViewModel()
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
                .fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(
                    text = "Добро пожаловать",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Войдите в свою учетную запись",
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
                            Box {
                                if (email.value.isBlank()) {
                                    Text(
                                        text = "Электронная почта",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                                it()
                            }
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
                            Box {
                                if (password.value.isBlank()) {
                                    Text(
                                        text = "Пароль",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                                it()
                            }
                            val icon = if (isVisible.value) {
                                Icons.Outlined.VisibilityOff
                            } else {
                                Icons.Outlined.Visibility
                            }
                            Icon(
                                imageVector = icon,
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable {
                                        isVisible.value = isVisible.value.not()
                                    }
                                    .size(25.dp),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }

                    }
                )
                Text(
                    text = "Забыли пароль?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            viewModel.onToForgetPassword()
                        }
                )
            }

            Button(
                onClick = {
                    viewModel.onToHome()
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
                    color = MaterialTheme.colorScheme.onPrimaryContainer,

                    )
            }
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "У вас нет учетной записи?",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Регистрироваться",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        viewModel.onToRegister()
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Divider(modifier = Modifier.weight(1f), thickness = 1.dp)
                Text(
                    text = "Или",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
                Divider(modifier = Modifier.weight(1f), thickness = 1.dp)
            }
            OutlinedButton(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = MaterialTheme.shapes.extraSmall,
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onSecondaryContainer),
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = "Войти с помощью Google",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )

            }
            OutlinedButton(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = MaterialTheme.shapes.extraSmall,
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onSecondaryContainer),
            ) {

                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = "Вход без аккаунта",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true,
    device = "id:pixel_4",
)
@Composable
fun PreviewScreenSignIn() {
    AnimeViewAppTheme {
        ScreenSignIn()

    }
}