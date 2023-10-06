package com.example.details

import android.util.Log
import android.view.WindowManager
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.PlayCircleFilled
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.disk.DiskCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.core.UrlImageVoice
import com.example.core.UrlServer
import com.example.core.model.ktor.SeriesModel

@Composable
fun ScreenDetails(animeId: Int) {
    val viewModel: ViewModelDetails = hiltViewModel()
    viewModel.setAnimeId(animeId)
//    WindowCompat.setDecorFitsSystemWindows(false)

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(bottom = 40.dp),

        ) {
        item {
            TopBar(viewModel = viewModel)
        }
//        item {
//            PlayerAndDownLoadSeries(viewModel = viewModel)
//        }
        item {
            Body(viewModel)
        }
        item {
            Series(viewModel)
        }
    }


}

@Composable
private fun LeftTopBar(viewModel: ViewModelDetails) {
    val nameModel by viewModel.nameModel.collectAsState()
    val voiceModel by viewModel.voiceModel.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(
            text = nameModel?.ru ?: "",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = voiceModel?.updated.toString() ?: "111111",
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun RightTopBar(viewModel: ViewModelDetails) {
    val voiceModel by viewModel.voiceModel.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        SubcomposeAsyncImage(
            model = voiceModel?.urlImagePreview,
            loading = {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            },
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        )

    }
}

@Composable
private fun TopBar(viewModel: ViewModelDetails) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxWidth()
                .height(260.dp)
                .align(Alignment.TopCenter),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(1f)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LeftTopBar(viewModel)
            RightTopBar(viewModel)
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 10.dp, start = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
    }


}

@Composable
fun Body(viewModel: ViewModelDetails) {
    val view by viewModel.anime.collectAsState()
    val nameModel by viewModel.nameModel.collectAsState()
    val voiceModel by viewModel.voiceModel.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth(0.9f),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Text(
                text = "Об аниме",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 16.sp
            )
            Row() {
                Text(text = "4.8", style = MaterialTheme.typography.labelMedium)
                Icon(imageVector = Icons.Rounded.StarRate, contentDescription = "")
            }
        }
        Row {
            Text(
                text = "Озвучка: ",
                style = MaterialTheme.typography.titleSmall,
                fontSize = 16.sp
            )
            Row {

                view?.voiceModels?.forEachIndexed { i, it ->

                    SubcomposeAsyncImage(
                        model = UrlServer + UrlImageVoice + "${it.voiceGrupe}.png",
                        loading = {},
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.setSelectViewVoice(i) }
                    )


                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            val string = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                        fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                    )
                ) {
                    append("Жанр:")
                }
                append(" ")
                withStyle(style = SpanStyle(fontStyle = MaterialTheme.typography.labelMedium.fontStyle)) {
                    append(voiceModel?.genre.toString())
                }

            }
            Text(text = string, fontSize = 16.sp)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            val string = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                        fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight
                    )
                ) {
                    append("Описание:")
                }
                append(" ")
                withStyle(style = SpanStyle(fontStyle = MaterialTheme.typography.labelMedium.fontStyle)) {
                    append(voiceModel?.description.toString())
                }

            }
            val isHideText = remember {
                mutableStateOf(false)
            }
            Text(
                text = string,
                fontSize = 16.sp,
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .clickable { isHideText.value = isHideText.value.not() },
                maxLines = if (isHideText.value) 200 else 4
            )
        }
    }
}

@Composable
fun Series(viewModel: ViewModelDetails) {
    val view by viewModel.anime.collectAsState()
    val seriesText by viewModel.seriesText.collectAsState()
    val scrollAnime by viewModel.scrollAnime.collectAsState()
    val listSeries by viewModel.listSeries.collectAsState()

    val listState = rememberLazyListState()
    LaunchedEffect(key1 = scrollAnime) {
        listState.animateScrollToItem(scrollAnime)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Episode",
            Modifier.fillMaxWidth(0.9f),
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = seriesText,
            onValueChange = {
                viewModel.setSeriesText(it)
            },
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                disabledIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                focusedTextColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSecondary,
                disabledTextColor = MaterialTheme.colorScheme.onSecondary
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            },
            placeholder = {
                Text(text = "Серия", color = MaterialTheme.colorScheme.onSecondary)
            },

            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default
        )


        val pading = LocalContext.current.resources.displayMetrics.xdpi / 10 / 2

        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = pading.dp),

            ) {


            items(listSeries) {

                ItemSeries(it = it, viewModel)
            }

        }
    }
}

@Composable
private fun ItemSeries(it: SeriesModel, viewModel: ViewModelDetails) {
    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
        val request: ImageRequest = ImageRequest.Builder(LocalContext.current.applicationContext)
            .data(it.preview)
//            .data(space.img)
            .crossfade(true)
//            .diskCacheKey(space.img)
            .diskCachePolicy(CachePolicy.ENABLED)
            .setHeader("Cache-Control", "max-age=31536000")
            .build()
        SubcomposeAsyncImage(
            model = request,
            loading = {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .height(120.dp)
                        .width(200.dp)
                        .align(Alignment.TopEnd)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(120.dp)
                .sizeIn(maxWidth = 200.dp, minWidth = 150.dp)
                .align(Alignment.TopEnd)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CutCornerShape(
                        bottomStart = 100.dp,
                    )
                ),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = it.name ?: "null",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 25.dp, end = 20.dp)
            )
        }

    }
}






