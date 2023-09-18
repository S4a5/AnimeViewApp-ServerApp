package com.example.home


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.core.model.ItemAnimeModel
import com.example.core.model.StateUi
import com.example.core.ui.theme.AnimeViewAppTheme
import com.example.core.ui.theme.ThemeBox

@Composable
fun ScreenHome(
    viewModelHome: ViewModelHome = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Search()

        RowGenre(viewModelHome)

        Content(viewModelHome)

    }
}

@Composable
fun Content(viewModelHome: ViewModelHome) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val listAnime by viewModelHome.list.collectAsState()
        val progress by viewModelHome.progressNewAnime.collectAsState()
        when (val state = listAnime) {
            is StateUi.Failed -> TODO()
            StateUi.Loader -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .align(Alignment.Center),
                        strokeWidth = 6.dp
                    )
                }

            }

            is StateUi.Success -> {
                val lazyState = rememberLazyListState()
                LazyColumn(
                    state = lazyState,
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    contentPadding = PaddingValues(vertical = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(state.data ?: emptyList()) {
                        Items(it)
                    }

                    if (progress is StateUi.Loader) {
                        item {

                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .fillMaxWidth(),
                                    strokeWidth = 6.dp
                                )

                        }
                    }

                }
                LaunchedEffect(lazyState) {
                    snapshotFlow { lazyState.layoutInfo }
                        .collect {
                            val lastVisibleItem =
                                lazyState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                            Log.e("avtoLoadAnime", lastVisibleItem.toString())
                            Log.e("avtoLoadAnime", lazyState.layoutInfo.totalItemsCount.toString())
                            Log.e("avtoLoadAnime", "==========================")
                            if (lastVisibleItem >= lazyState.layoutInfo.totalItemsCount - 2) {
                                viewModelHome.avtoLoadAnime()
                            }
                        }
                }

            }
        }

    }

}

@Composable
private fun Items(data: ItemAnimeModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SubcomposeAsyncImage(
            model = data.previewImageUrl,
            loading = {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .fillMaxHeight(0.3f)
                .clip(RoundedCornerShape(10.dp))
                .weight(0.05f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val title = data.nameEng

            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(0.9f),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
            )

            if (data.ordinal != null) {
                val season = data.ordinal.toString()
                Text(
                    text = season,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                text = data.genre.joinToString(separator = ", "),
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (model in data.voice) {

                    Image(
                        painter = painterResource(id = model.image),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun Search() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 10.dp)
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

@Composable
fun RowGenre(viewModelHome: ViewModelHome) {
    val display = (LocalContext.current.resources.displayMetrics.xdpi * 0.05).toInt()
    val genre by viewModelHome.genre.collectAsState()
    val isSelect = viewModelHome.selectGenre

    when (val result = genre) {
        StateUi.Loader -> {
            CircularProgressIndicator(modifier = Modifier.height(30.dp))
        }

        is StateUi.Success -> {
            val data = result.data
            if (data != null) {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = display.dp),
                    modifier = Modifier.padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val sortData =
                        data.entries.sortedByDescending { isSelect.find { a -> it.key == a } != null }

                    for ((key, value) in sortData) {

                        if (key.toInt() <= 29) {
                            item() {
                                if (isSelect.find { it == key } != null) {
                                    Box(
                                        modifier = Modifier
                                            .height(30.dp)
                                            .padding(horizontal = 0.dp)
                                            .border(
                                                1.dp, MaterialTheme.colorScheme.primary,
                                                RoundedCornerShape(20.dp)
                                            )
                                            .background(
                                                MaterialTheme.colorScheme.primary,
                                                RoundedCornerShape(20.dp)
                                            )
                                            .clickable {
                                                viewModelHome.onClickGenre(key)
                                            },
                                        contentAlignment = Alignment.Center,
                                    )
                                    {
                                        Text(
                                            text = value,
                                            modifier = Modifier.padding(horizontal = 10.dp),
                                            color = MaterialTheme.colorScheme.background
                                        )
                                    }
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .height(30.dp)
                                            .padding(horizontal = 0.dp)
                                            .border(
                                                1.dp, MaterialTheme.colorScheme.secondaryContainer,
                                                RoundedCornerShape(20.dp)
                                            )
                                            .background(
                                                MaterialTheme.colorScheme.secondary,
                                                RoundedCornerShape(20.dp)
                                            )
                                            .clickable {
                                                viewModelHome.onClickGenre(key)
                                            },
                                        contentAlignment = Alignment.Center,
                                    )
                                    {

                                        Text(
                                            text = value,
                                            modifier = Modifier.padding(horizontal = 10.dp),
                                            color = MaterialTheme.colorScheme.secondaryContainer
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        else -> {

        }
    }

}

@Composable
@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFFFFFFF)
fun PreviewScreenHomeItems() {
    AnimeViewAppTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Search()
//            RowGenre()
        }
    }
}
//@Composable
//@Preview
//fun PreviewScreenHome() {
//    AnimeViewAppTheme {
//        ScreenHome()
//    }
//}