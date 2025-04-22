package com.prashant.uialert.event

import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import app.debugdesk.uialerts.event.ProgressTextPosition
import com.prashant.uialert.model.AlertDialogColorProperties
import kmpcleanarchitecturetemplate.ui_alert.generated.resources.Res
import kmpcleanarchitecturetemplate.ui_alert.generated.resources.cancel
import kmpcleanarchitecturetemplate.ui_alert.generated.resources.ok
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Sealed interface for events related to UI alerts.
 * This interface defines different types of alerts that can be shown in the UI.
 * It includes SnackBar, Dialog, and Toast events.
 * @author DebugDesk
 */

sealed interface AlertEvent {
    data class SnackBarHost(
        val message: String? = null,
        val actionLabel: String? = null,
        val duration: SnackbarDuration = SnackbarDuration.Short,
        val alertType: AlertType = AlertType.INFO,
        val containerColor: Color = Color.Unspecified,
        val contentColor: Color = Color.Unspecified,
        val actionColor: Color = Color.Unspecified,
        val actionContentColor: Color = Color.Unspecified,
        val onDismiss: (() -> Unit)? = null,
        val onAction: (() -> Unit)? = null,
    ) : AlertEvent

    sealed interface DialogAlert : AlertEvent {
        data class Dialog(
            val dialogIcon: DrawableResource? = null,
            val title: String,
            val message: String,
            val alertType: AlertType = AlertType.INFO,
            val shape: Shape = ShapeDefaults.Medium,
            val dismissOnBackPress: Boolean = true,
            val dismissOnClickOutside: Boolean = true,
            val tonalElevation: Dp = Dp.Hairline,
            val confirmButtonText: StringResource = Res.string.ok,
            val cancelButtonText: StringResource = Res.string.cancel,
            val dialogColorProperties: AlertDialogColorProperties = AlertDialogColorProperties(),
            val onConfirm: () -> Unit = {},
            val onCancel: () -> Unit = {},
        ) : DialogAlert

        data class CircularIndicator(
            val text: String? = null,
            val textPosition: ProgressTextPosition = ProgressTextPosition.BOTTOM,
        ) : DialogAlert

        data class LinearIndicator(
            val message: String? = null,
            val progress: Float? = null,
        ) : DialogAlert
    }
}
