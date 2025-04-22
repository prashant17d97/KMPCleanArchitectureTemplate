package app.prashant.cleankmp

import androidx.compose.ui.window.ComposeUIViewController
import app.prashant.cleankmp.di.initiateKoin
import app.prashant.cleankmp.presentation.App

fun mainViewController() =
    ComposeUIViewController(
        configure = { initiateKoin() },
        content = { App() },
    )
