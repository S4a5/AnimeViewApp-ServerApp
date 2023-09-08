package com.example.sing_in

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.AnimeViewAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSignIn() {
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
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Column(Modifier.padding(top = 20.dp)) {
                Text(
                    text = "Добро пожаловать",
                    modifier = Modifier.padding(top = 10.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Войдите в свою учетную запись",
                    style = MaterialTheme.typography.labelMedium,
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
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                OutlinedTextField(
                    value = email.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    textStyle = MaterialTheme.typography.labelMedium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondaryContainer

                    ),
                    onValueChange = {
                        email.value = it
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Пароль",
                    style = MaterialTheme.typography.titleSmall,
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
                    visualTransformation = if (isVisible.value) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                    decorationBox = {

                        val icon = if (isVisible.value) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
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
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
                Text(
                    text = "забыли пароль?",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            Button(
                onClick = {

                },
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Авторизоваться",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primaryContainer,

                    )
            }
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "У вас нет учетной записи?",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onBackground,
//                        modifier = Modifier.align(Alignment.End)
                )
                Text(
                    text = "Регистрироваться",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
//                        modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true,
)
@Composable
fun PreviewScreenSignIn() {
    AnimeViewAppTheme {
        ScreenSignIn()
    }
}