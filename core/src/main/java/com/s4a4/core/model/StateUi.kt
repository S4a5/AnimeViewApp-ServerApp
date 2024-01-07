package com.s4a4.core.model

sealed class StateUi<out T> {
    data class Success<out T> (val data:T? = null):StateUi<T>()
    object Loader : StateUi<Nothing>()
    class Failed(val error: String? = null) : StateUi<Nothing>()
}