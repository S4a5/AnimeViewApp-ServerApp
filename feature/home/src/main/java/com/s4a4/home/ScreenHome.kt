package com.s4a4.home


import android.app.Activity
import android.content.pm.ActivityInfo
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.s4a4.core.UrlImageVoice
import com.s4a4.core.UrlServer
import com.s4a4.core.model.StateUi
import com.s4a4.core.model.ktor.AnimeDetails
import com.s4a4.core.ui.theme.AnimeViewAppTheme
import com.s4a4.core.ui.theme.ThemeBox
import kotlinx.coroutines.launch

@Composable
fun ScreenHome(
    viewModelHome: ViewModelHome = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Search(viewModelHome)

        RowGenre(viewModelHome)

        Content(viewModelHome)

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Content(viewModelHome: ViewModelHome) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val stateUi by viewModelHome.stateUi.collectAsState()
        val reFreshStateUi by viewModelHome.reFreshStateUi.collectAsState()
        val listAnime by viewModelHome.viewList.collectAsState()
        val progress by viewModelHome.progressNewAnime.collectAsState()

        when (val state = stateUi) {
            is StateUi.Failed -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.error_500),
                            contentDescription = "",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                        Button(
                            onClick = {
                                viewModelHome.getData()
                            },
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.extraSmall),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(
                                text = "Переподключение",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,

                                )
                        }
                    }

                }
            }

            is StateUi.Success -> {
                val lazyState = rememberLazyListState()
                if (listAnime.isNotEmpty()) {
                    val refreshScope = rememberCoroutineScope()

                    fun refresh() = refreshScope.launch {
                        viewModelHome.refreshData()
                    }

                    val pullRefreshState =
                        rememberPullRefreshState(
                            refreshing = reFreshStateUi is StateUi.Loader,
                            onRefresh = { refresh() })
                    Box(Modifier.pullRefresh(pullRefreshState)) {

                        LazyColumn(
                            state = lazyState,
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            contentPadding = PaddingValues(vertical = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            itemsIndexed(listAnime) { index, data ->

                                Items(data, viewModelHome)
                            }
                            when (progress) {
                                is StateUi.Failed -> {
                                    item {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Text(text = "Нет подключение к серверу")
                                            Button(
                                                onClick = {
                                                    viewModelHome.avtoLoadAnime()
                                                },
                                                modifier = Modifier
                                                    .clip(MaterialTheme.shapes.extraSmall),
                                                shape = MaterialTheme.shapes.medium
                                            ) {
                                                Text(
                                                    text = "Повторить попытку",
                                                    style = MaterialTheme.typography.titleSmall,
                                                    color = MaterialTheme.colorScheme.onPrimaryContainer,

                                                    )
                                            }
                                        }

                                    }

                                }

                                StateUi.Loader -> {
                                    item {

                                        CircularProgressIndicator(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .fillMaxWidth(),
                                            strokeWidth = 6.dp
                                        )

                                    }
                                }

                                is StateUi.Success -> {

                                }
                            }

                        }
                        PullRefreshIndicator(
                            refreshing = reFreshStateUi is StateUi.Loader,
                            state = pullRefreshState,
                            modifier = Modifier.align(
                                Alignment.TopCenter
                            )
                        )
                    }

                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.5f),
                        contentAlignment = Alignment.Center
                    ) {


                        Text(
                            text = "Нет результатов",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                    }

                }

                LaunchedEffect(lazyState) {
                    snapshotFlow { lazyState.layoutInfo }
                        .collect {
                            Log.d("qweqwrqwrq", lazyState.firstVisibleItemIndex.toString())
                            val lastVisibleItem =
                                lazyState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                            if (lastVisibleItem >= lazyState.layoutInfo.totalItemsCount - 2) {
                                viewModelHome.avtoLoadAnime()
                            }
                        }
                }

            }

            is StateUi.Loader -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize(0.5f)
                            .align(Alignment.Center),
                        strokeWidth = 6.dp
                    )
                }

            }

            else -> {
                Text(text = "Что то не так")
            }
        }

    }

}

@Composable
private fun Items(data: AnimeDetails, viewModelHome: ViewModelHome) {

    Log.d("qqqqwwwwwwweeee", data.nameModels.size.toString())
    val nameModels = data.nameModels.first()
    val voiceModels = data.voiceModels.first()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable {
                viewModelHome.selectAnime(data.voiceModels.last().anime_id)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        SubcomposeAsyncImage(
            model = voiceModels.urlImagePreview,
            loading = {
                Icon(
                    painter = painterResource(id = com.s4a4.core.R.drawable.ic_no_image),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .height(175.dp)
                        .fillMaxWidth(0.35f)
                )
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(175.dp)
                .fillMaxWidth(0.35f)
                .clip(RoundedCornerShape(10.dp))
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val title = nameModels.ru!!

            val part = if (nameModels.part != null) {
                ("Часть " + nameModels.part) ?: ""
            } else {
                ""
            }
            val season = if (nameModels.season != null && nameModels.season != "1") {
                (" " + nameModels.season + " Сезон ") ?: ""
            } else {
                ""
            }

            Text(
                text = title + season + part,
                modifier = Modifier.fillMaxWidth(0.9f),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
            )

//            if (data.ordinal != null) {
//                val season = data.ordinal.toString()
//                Text(
//                    text = season,
//                    style = MaterialTheme.typography.labelMedium,
//                    textAlign = TextAlign.Center,
//                )
//            }
//            Text(
//                text = data.genre.joinToString(separator = ", "),
//                style = MaterialTheme.typography.labelMedium,
//                textAlign = TextAlign.Center,
//            )
            if (voiceModels.genre != null) {
                Text(
                    text = voiceModels.genre.toString() ?: "NULL",
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                data.voiceModels.forEachIndexed { i, it ->

                    SubcomposeAsyncImage(
                        model = UrlServer + UrlImageVoice + "${it.voiceGrupe}.png",
                        loading = {},
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
        }

    }
}

@Composable
fun Search(viewModelHome: ViewModelHome) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 10.dp)
    ) {
        Column {
            val searchQuery by viewModelHome.searchQuery.collectAsState()
            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    viewModelHome.setSearchQuery(it)
                },
                singleLine = true,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                decorationBox = {
                    ThemeBox(0.9f) {
                        Box {
                            if (searchQuery.isBlank()) {
                                Text(
                                    text = "Поиск",
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
        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "")

    }

}

@Composable
fun RowGenre(viewModelHome: ViewModelHome) {
    val display = (LocalContext.current.resources.displayMetrics.xdpi * 0.05).toInt()
    val genre by viewModelHome.genre.collectAsState()
    val isSelect by viewModelHome.selectGenre.collectAsState()

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
                        data.sortedByDescending { isSelect.find { a -> it == a } != null }

                    items(sortData) { value ->
                        if (isSelect.find { it == value } != null) {
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
                                        viewModelHome.onClickGenre(value)
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
                                        viewModelHome.onClickGenre(value)
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

        else -> {

        }
    }

}


@Composable
@Preview(
    device = "id:pixel_4a",
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
fun PreviewScreenHome() {
    AnimeViewAppTheme {

    }
}