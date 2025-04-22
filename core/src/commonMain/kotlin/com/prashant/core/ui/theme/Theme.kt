package com.prashant.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme =
    lightColorScheme(
        primary = Color(0xFF404040),
        // primaryNeutral
        onPrimary = Color(0xFFFFFFFF),
        // white text on dark button
        primaryContainer = Color(0xFFF4F4F5),
        // Zinc100 as a soft container
        onPrimaryContainer = Color(0xFF000000),
        secondary = Color(0xFFD4D4D7),
        // Zinc300
        onSecondary = Color(0xFF000000),
        secondaryContainer = Color(0xFFE4E4E7),
        // Zinc200
        onSecondaryContainer = Color(0xFF000000),
        background = Color(0xFFFFFFFF),
        // white
        onBackground = Color(0xFF27272A),
        // Zinc800 for contrast text
        surface = Color(0xFFFFFFFF),
        // white surface
        onSurface = Color(0xFF404040),
        // neutral gray
        error = Color(0xFFEF4444),
        // red-500
        onError = Color(0xFFFFFFFF),
        outline = Color(0xFF71717A),
        // Zinc500
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = Color(0xFFF4F4F5),
        // Zinc100 as light primary on dark
        onPrimary = Color(0xFF27272A),
        // Zinc800 for contrast text
        primaryContainer = Color(0xFF404040),
        // primaryNeutral as container
        onPrimaryContainer = Color(0xFFFFFFFF),
        secondary = Color(0xFFE4E4E7),
        // Zinc200
        onSecondary = Color(0xFF000000),
        secondaryContainer = Color(0xFF71717A),
        // Zinc500
        onSecondaryContainer = Color(0xFFFFFFFF),
        background = Color(0xFF000000),
        // black
        onBackground = Color(0xFFF4F4F5),
        // Zinc100 for text
        surface = Color(0xFF000000),
        // black surface
        onSurface = Color(0xFFD4D4D7),
        // Zinc300 for text
        error = Color(0xFFEF4444),
        // red-500
        onError = Color(0xFFFFFFFF),
        outline = Color(0xFF71717A),
        // Zinc500
    )

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
//            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//                val context = LocalContext.current
//                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
