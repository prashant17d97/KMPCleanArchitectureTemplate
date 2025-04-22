package com.prashant.uialert.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.prashant.uialert.event.AlertType

object AlertUtils {
    /**
     * Utility functions to handle alert types and their properties.
     */
    private val AlertType.getSnackBarContainerColor: Color
        @Composable
        get() {
            return when (this) {
                AlertType.INFO -> MaterialTheme.colorScheme.surfaceContainer
                AlertType.WARNING -> MaterialTheme.colorScheme.secondaryContainer
                AlertType.ERROR -> MaterialTheme.colorScheme.error
                AlertType.SUCCESS -> MaterialTheme.colorScheme.primaryContainer
            }
        }

    @Composable
    fun Color.value(alertType: AlertType): Color {
        return if (this == Color.Unspecified) alertType.getSnackBarContainerColor else this
    }

    @Composable
    fun Color.valueContent(alertType: AlertType): Color {
        return if (this == Color.Unspecified) contentColorFor(alertType.getSnackBarContainerColor) else this
    }

    val AlertType.dialogButtonColor: Color
        @Composable
        get() {
            return when (this) {
                AlertType.INFO -> MaterialTheme.colorScheme.primary
                AlertType.WARNING -> MaterialTheme.colorScheme.onSecondaryContainer
                AlertType.ERROR -> MaterialTheme.colorScheme.onError
                AlertType.SUCCESS -> MaterialTheme.colorScheme.primary
            }
        }

    val AlertType.dialogButtonContentColor: Color
        @Composable
        get() {
            return when (this) {
                AlertType.INFO -> MaterialTheme.colorScheme.onPrimary
                AlertType.WARNING -> MaterialTheme.colorScheme.onSecondaryContainer
                AlertType.ERROR -> MaterialTheme.colorScheme.onError
                AlertType.SUCCESS -> MaterialTheme.colorScheme.onPrimary
            }
        }
}
