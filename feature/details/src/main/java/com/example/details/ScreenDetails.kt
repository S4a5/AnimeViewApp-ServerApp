package com.example.details

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import org.junit.internal.builders.AnnotatedBuilder

@Composable
fun ScreenDetails(animeId: Int) {
    val viewModel: ViewModelDetails = hiltViewModel()
    viewModel.setAnimeId(animeId)
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(bottom = 40.dp)
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
fun Series(viewModel: ViewModelDetails) {
    val view by viewModel.anime.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Episode",
            Modifier.fillMaxWidth(0.9f),
            style = MaterialTheme.typography.titleMedium
        )
        val textSearch = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = textSearch.value,
            onValueChange = {
                textSearch.value = it
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
            singleLine = true
        )

        val pading = LocalContext.current.resources.displayMetrics.xdpi / 10 / 2

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = pading.dp)
        ) {
            items(view?.seriesModels?.last() ?: emptyList()) {
                SubcomposeAsyncImage(
                    model = it.preview,
                    loading = {
//
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.onSecondary)
                                .height(120.dp)
//                                        .width(200.dp)
                                .clip(RoundedCornerShape(10.dp))
                        ) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(120.dp)
//                                .fillMaxWidth(0.2f)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
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
                var i = 0
                view?.voiceModels?.forEach{

                    if (it.voiceGrupe == "anime_vost"){
                        val a = i
                        Icon(
                            painter = painterResource(id = com.example.core.R.drawable.anime_vost),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp).clickable { viewModel.setSelectViewVoice(a) }
                        )
                    }
                    if (it.voiceGrupe == "anilibria"){
                        val a = i
                        Icon(
                            painter = painterResource(id = com.example.core.R.drawable.anilibria),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp).clickable { viewModel.setSelectViewVoice(a) }
                        )
                    }
                    i++
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
private fun PlayerAndDownLoadSeries(viewModel: ViewModelDetails) {
    Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {

        }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayCircleFilled,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Text(text = "Смотреть", color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        OutlinedButton(onClick = {

        }, border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.CloudDownload,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(text = "Скачать", color = MaterialTheme.colorScheme.primary)
            }
        }
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
private fun LeftTopBar(viewModel: ViewModelDetails) {
    val nameModel by viewModel.nameModel.collectAsState()
    val voiceModel by viewModel.voiceModel.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
        Text(
            text = nameModel?.ru ?:"",
            style = MaterialTheme.typography.titleMedium
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

