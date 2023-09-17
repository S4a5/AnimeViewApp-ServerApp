package com.example.core.model

sealed class StateUi<out T> {
    data class Success<out T> (val data:T?):StateUi<T>()
    object Loader : StateUi<Nothing>()

    class Failed(val error: String? = null) : StateUi<Nothing>()
}