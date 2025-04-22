package com.prashant.uialert.event

sealed class ToastDuration(val duration: Int) {
    data object Short : ToastDuration(4000)

    data object Long : ToastDuration(10000)
}
