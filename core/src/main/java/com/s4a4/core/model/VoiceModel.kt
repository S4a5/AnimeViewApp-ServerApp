package com.s4a4.core.model

import androidx.annotation.DrawableRes

data class VoiceModel (
    val voice:String,
    val timeNewSeries:Int,
    @DrawableRes val image:Int
)