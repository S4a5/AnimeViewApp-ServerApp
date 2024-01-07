package com.s4a4.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.s4a4.core.ui.theme.Gray_4
import com.s4a4.player.data.model.Definition
import kotlinx.coroutines.delay

@Composable
fun CustomVideoController(
    player: ExoPlayer,
    modifier: Modifier = Modifier,
    viewModelVideoPlayer: ViewModelVideoPlayer
) {
    val viewModel = hiltViewModel<ViewModelController>()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val isVisibleController by viewModel.isVisible.collectAsState()


    LaunchedEffect(key1 = null) {
        viewModel.setExoPlayer(player)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                viewModel.setVisibleController()
            }
    ) {
        AnimatedVisibility(
            visible = isVisibleController,
            enter = fadeIn(tween(2000)),
            exit = fadeOut(tween(2000))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0, 0, 0, 100))
            )
        }

    }


    Box(
        modifier = modifier
    ) {

        AnimatedVisibility(
            visible = isVisibleController, modifier = Modifier
                .align(Alignment.Center),
            enter = fadeIn(tween(1000)),
            exit = fadeOut(tween(1000))
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        viewModel.setVisibleController()
                        viewModel.setIsPlaying()
                    }
                    .size(60.dp)
                    .background(Color(0, 0, 0, 100)),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        // Кнопка Play/Pause
        val progress = remember {
            mutableLongStateOf(0L)
        }
        val max = remember {
            mutableLongStateOf(player.duration)
        }
        val progressInFloat =
            remember { mutableFloatStateOf((progress.longValue.toFloat()) / (max.longValue.toFloat())) }
        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(100)
                progressInFloat.floatValue =
                    (progress.longValue.toFloat()) / (max.longValue.toFloat())
                progress.longValue = player.currentPosition
                max.longValue = player.duration
                if (!isPlaying) continue
            }
        }

        AnimatedVisibility(
            visible = isVisibleController, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Slider(
                    value = progressInFloat.floatValue,
                    onValueChange = {
                        viewModel.setVisibleController()
                        val float = (it.toFloat()) * (max.longValue.toFloat())
                        player.seekTo(float.toLong())
                    },
                    colors = SliderDefaults.colors(
                        disabledActiveTrackColor = Gray_4,
                        inactiveTrackColor = Gray_4
                    )
                )

                // Текст с текущим временем/длительностью
                Text(
                    text = "${progress.longValue.toTime()} / ${max.longValue.toTime()}",
                    color = Color.White
                )
            }
        }
        AnimatedVisibility(
            visible = isVisibleController,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                ViewVoice(
                    viewModelVideoPlayer,
                )
                ViewEpisode(
                    viewModelVideoPlayer,
                )
            }
        }
        AnimatedVisibility(
            visible = isVisibleController,
            enter = expandVertically(),
            exit = shrinkVertically(),
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            ViewDefinition(
                modifier = Modifier,
                viewModelVideoPlayer,
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewVoice(
    viewModelVideoPlayer: ViewModelVideoPlayer,
) {
    val listVoice by viewModelVideoPlayer.listVoice.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val selectedText by viewModelVideoPlayer.selectVoice.collectAsState()

    Box {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            Row(
                modifier = Modifier
                    .menuAnchor()
                    .background(
                        Gray_4,
                        RoundedCornerShape(4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedText ?: "ERROR",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Gray_4)
                    .wrapContentWidth()
            ) {
                listVoice.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = Color.White,
                                modifier = Modifier.wrapContentWidth(unbounded = true)
                            )
                        },
                        onClick = {
                            viewModelVideoPlayer.setSelectVoice(item)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewEpisode(
    viewModelVideoPlayer: ViewModelVideoPlayer,
) {
    var expanded by remember { mutableStateOf(false) }

    val listSeriesCurrentVoice by viewModelVideoPlayer.listSeriesCurrentVoice.collectAsState()

    val currentEpisodeSeriesModel by viewModelVideoPlayer.currentEpisodeSeriesModel.collectAsState()


    Column {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            Row(
                modifier = Modifier
                    .menuAnchor()
                    .background(
                        Gray_4,
                        RoundedCornerShape(4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentEpisodeSeriesModel?.name ?: "ERROR",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }

//            TextField(
//                value = selectedText,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                modifier = Modifier.menuAnchor()
//            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Gray_4)
                    .wrapContentWidth()
            ) {
                listSeriesCurrentVoice.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item.name ?: "null",
                                color = Color.White,
                                modifier = Modifier.wrapContentWidth(unbounded = true)
                            )
                        },
                        onClick = {
                            viewModelVideoPlayer.setSelectEpisode(item.id)
                            expanded = false
                        },
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewDefinition(
    modifier: Modifier,
    viewModelVideoPlayer: ViewModelVideoPlayer,
) {
    val listSeriesCurrentVoice by viewModelVideoPlayer.listSeriesCurrentVoice.collectAsState()

    var expanded by remember { mutableStateOf(false) }
    val selectEpisode by viewModelVideoPlayer.selectEpisodeId.collectAsState()
    val currentEpisodeSeriesModel by viewModelVideoPlayer.currentEpisodeSeriesModel.collectAsState()
    val currentDefinition by viewModelVideoPlayer.currentDefinition.collectAsState()
    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            Row(
                modifier = Modifier
                    .menuAnchor()
                    .background(
                        Gray_4,
                        RoundedCornerShape(4.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = when (currentDefinition) {
                        is Definition.FHD -> "FHD"
                        is Definition.HD -> "HD"
                        is Definition.SD -> "SD"
                        null -> "null"
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                    modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Center
                )
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
            val fhd by remember {
                mutableStateOf(currentEpisodeSeriesModel?.fhd)
            }
            val hd by remember {
                mutableStateOf(currentEpisodeSeriesModel?.hd)
            }
            val std by remember {
                mutableStateOf(currentEpisodeSeriesModel?.std)
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Gray_4)
            ) {
                if (fhd != null) {
                    DropdownMenuItem(
                        text = { Text(text = "FHD", color = Color.White) },
                        onClick = {
                            viewModelVideoPlayer.setSelectDefinition(Definition.FHD(fhd ?: "null"))
                            expanded = false
                        },
                    )
                }
                if (hd != null) {
                    DropdownMenuItem(
                        text = { Text(text = "HD", color = Color.White) },
                        onClick = {
                            viewModelVideoPlayer.setSelectDefinition(Definition.HD(hd ?: "null"))
                            expanded = false
                        },
                    )
                }
                if (std != null) {
                    DropdownMenuItem(
                        text = { Text(text = "STD", color = Color.White) },
                        onClick = {
                            viewModelVideoPlayer.setSelectDefinition(Definition.SD(std ?: "null"))
                            expanded = false
                        },
                    )
                }
            }

        }
    }
}

private fun Long.toTime(): String {
    val hours = this / 1000 / 3600
    val minutes = (this / 1000 % 3600) / 60
    val seconds = this / 1000 % 60

    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}