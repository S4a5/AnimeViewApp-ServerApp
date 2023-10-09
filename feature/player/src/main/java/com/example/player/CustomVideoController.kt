package com.example.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.core.ui.theme.AnimeViewAppTheme
import kotlinx.coroutines.delay

@Composable
fun CustomVideoController(
    player: ExoPlayer,
    modifier: Modifier = Modifier,
    isStartVisibleController: Boolean
) {
    val viewModel = hiltViewModel<ViewModelController>()

    val playbackState by rememberUpdatedState(newValue = player)
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
                )

                // Текст с текущим временем/длительностью
                Text(
                    text = "${progress.longValue.toTime()} / ${max.longValue.toTime()}",
                    color = Color.White
                )
            }
        }
        ViewVoice(modifier = Modifier.background(Color.White))
    }

}

@Preview
@Composable
fun PreviewViewVoice() {
    AnimeViewAppTheme {
        ViewVoice(Modifier)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewVoice(modifier: Modifier) {
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
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
                        MaterialTheme.colorScheme.background,
                        RoundedCornerShape(4.dp)
                    )
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp))
                    , verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = selectedText, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary,modifier = Modifier.padding(4.dp))
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

            ) {
                coffeeDrinks.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = MaterialTheme.colorScheme.primary,
                            leadingIconColor = MaterialTheme.colorScheme.primary,
                            trailingIconColor = MaterialTheme.colorScheme.primary
                        )
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