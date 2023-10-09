import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.example.core.model.ktor.SeriesModel
import com.example.player.CustomVideoController
import com.example.player.ViewModelVideoPlayer
import com.example.player.data.model.Definition


@Composable
fun ScreenVideoPlayer(id: Int, voice: String, episode: Int) {
    val activity = LocalContext.current as Activity
    val color = MaterialTheme.colorScheme.primary
    val viewModel = hiltViewModel<ViewModelVideoPlayer>()
    viewModel.setArgument(id, voice, episode)

    val currentEpisodeUrl by viewModel.currentEpisodeUrl.collectAsState()
    val listSeriesCurrentVoice by viewModel.listSeriesCurrentVoice.collectAsState()

    if (currentEpisodeUrl != null) {
        Log.d("qqqqqqqqq2", currentEpisodeUrl.toString())
        VideoPlayer(listSeriesCurrentVoice, viewModel)
    }


    DisposableEffect(Unit) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        activity.hideSystemUI(color)

        onDispose {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

}

@SuppressLint("OpaqueUnitKey")
@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun VideoPlayer(sourceUrl: List<SeriesModel>, viewModel: ViewModelVideoPlayer) {

    val context = LocalContext.current
    val definition by viewModel.definition.collectAsState()
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                    context,
                    defaultDataSourceFactory
                )
                val listSource = sourceUrl.map {

                    val url = when (val result = definition) {
                        is Definition.FHD -> MediaItem.fromUri(result.url)
                        is Definition.HD -> MediaItem.fromUri(result.url)
                        is Definition.SD -> MediaItem.fromUri(result.url)
                        null -> TODO()
                    }
                    ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(url)
                }

                setMediaSources(listSource)
                prepare()
            }
    }

    exoPlayer.playWhenReady = false
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE


    Box(modifier = Modifier.fillMaxSize()) {
        DisposableEffect(

            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        hideController()
                        useController = false
                        player = exoPlayer
                        setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS)

                    }

                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            )

        ) {

            onDispose { exoPlayer.release() }
        }
        CustomVideoController(
            exoPlayer, modifier = Modifier
                .fillMaxSize(0.95f)
                .align(Alignment.Center), true
        )
    }

}

fun Activity.hideSystemUI(color: Color) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.let {

            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            window.navigationBarColor = color.hashCode()

            it.hide(WindowInsets.Type.systemBars())
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (

                View.SYSTEM_UI_FLAG_IMMERSIVE

                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN

                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

        @Suppress("DEPRECATION")
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }
}
