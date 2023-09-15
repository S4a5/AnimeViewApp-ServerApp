package com.example.home


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.core.ui.theme.ThemeBox

@Composable
fun ScreenHome() {
    val viewModelHome: ViewModelHome = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Search()

        RowGenre()

        Content(viewModelHome)

    }
}

@Composable
fun Content(viewModelHome: ViewModelHome) {
    val listAnime by viewModelHome.list.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxWidth()
    ) {
        items(listAnime?.data ?: emptyList()) {
            Text(text = it.title)
        }
    }
}

@Composable
fun Search() {

    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
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

}

@Composable
fun RowGenre() {
    val list = arrayListOf<String>(
        "Genre1",
        "Genre2",
        "Genre3",
        "Genre4",
        "Genre5",
        "Genre6",
        "Genre7",
        "Genre8",
        "Genre9"
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 5.dp),
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        items(list) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .padding(horizontal = 5.dp)
                    .border(
                        1.dp, MaterialTheme.colorScheme.secondaryContainer,
                        RoundedCornerShape(20.dp)
                    )
                    .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = it,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewScreenHome() {
    AnimeViewAppTheme {
        ScreenHome()
    }
}