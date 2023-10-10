package com.example.player

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.core.model.VoiceModel
import com.example.player.data.GetAnimeByIdRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val VISIBLE_SECOND = 2

@HiltViewModel
class ViewModelController @Inject constructor() : ViewModel() {
    private val _isVisible = MutableStateFlow(true)
    val isVisible = _isVisible.asStateFlow()
    private val _timeHide = mutableIntStateOf(VISIBLE_SECOND)
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _exoPlayer = MutableStateFlow<ExoPlayer?>(null)

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {

            }
        }
        _isVisible.onEach {

            while (_isVisible.value) {
                delay(1000)
                if (!_isPlaying.value) continue
                _timeHide.intValue--
                if (_timeHide.intValue <= 0) {
                    _isVisible.value = false
                }
            }


        }.launchIn(viewModelScope)
    }

    fun setVisibleController() {
        if (_isVisible.value) {
            _timeHide.intValue = VISIBLE_SECOND
        } else {
            _timeHide.intValue = VISIBLE_SECOND
            _isVisible.value = true

        }
    }

    fun setIsPlaying() {
        if (_exoPlayer.value?.isPlaying == true) {
            _exoPlayer.value?.pause()
            _isPlaying.value = false
        } else {
            _exoPlayer.value?.play()
            _isPlaying.value = true
        }

    }

    fun setExoPlayer(player: ExoPlayer) {
        _exoPlayer.value = player
    }

}