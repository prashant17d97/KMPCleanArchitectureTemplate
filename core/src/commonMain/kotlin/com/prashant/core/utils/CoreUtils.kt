package com.prashant.core.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.prashant.core.domain.models.enums.ThemeMode
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

object CoreUtils {
    val StringResource.value: String
        @Composable
        get() = stringResource(this)

    val DrawableResource.icon: Painter
        @Composable
        get() = painterResource(this)

    val ThemeMode.darkMode: Boolean
        @Composable
        get() =
            when (this) {
                ThemeMode.DARK -> true
                ThemeMode.LIGHT -> false
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }
}
