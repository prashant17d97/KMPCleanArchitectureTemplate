package com.prashant.uialert.impl

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import app.debugdesk.uialerts.event.ProgressTextPosition
import com.prashant.uialert.api.AlertDispatcher
import com.prashant.uialert.event.AlertEvent
import com.prashant.uialert.event.AlertType
import com.prashant.uialert.model.AlertDialogColorProperties
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

class AlertDispatcherImpl : AlertDispatcher {
    private val snackbarStateFlow = MutableStateFlow<AlertEvent.SnackBarHost?>(null)
    override val snackBarHost: StateFlow<AlertEvent.SnackBarHost?> = snackbarStateFlow

    private val dialogStateFlow = MutableStateFlow<AlertEvent.DialogAlert?>(null)
    override val dialog: StateFlow<AlertEvent.DialogAlert?> = dialogStateFlow

    override fun showAlertDialog(
        dialogIcon: DrawableResource?,
        title: String,
        message: String,
        alertType: AlertType,
        shape: Shape,
        dismissOnBackPress: Boolean,
        dismissOnClickOutside: Boolean,
        tonalElevation: Dp,
        confirmButtonText: StringResource,
        cancelButtonText: StringResource,
        dialogColorProperties: AlertDialogColorProperties,
        onConfirm: () -> Unit,
        onCancel: () -> Unit,
        hideOnConfirm: Boolean,
    ) {
        val onConfirmAction: () -> Unit = {
            onConfirm()
            if (hideOnConfirm) {
                dialogStateFlow.value = null
            }
        }
        val onCancelAction: () -> Unit = {
            onCancel()
            dialogStateFlow.value = null
        }
        val dialogHost =
            AlertEvent.DialogAlert.Dialog(
                dialogIcon = dialogIcon,
                title = title,
                message = message,
                alertType = alertType,
                shape = shape,
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside,
                tonalElevation = tonalElevation,
                confirmButtonText = confirmButtonText,
                cancelButtonText = cancelButtonText,
                dialogColorProperties = dialogColorProperties,
                onConfirm = onConfirmAction,
                onCancel = onCancelAction,
            )
        dialogStateFlow.tryEmit(dialogHost)
    }

    override fun showSnackBar(
        message: String?,
        actionLabel: String?,
        duration: SnackbarDuration,
        alertType: AlertType,
        containerColor: Color,
        contentColor: Color,
        actionColor: Color,
        actionContentColor: Color,
        onDismiss: (() -> Unit)?,
        onAction: (() -> Unit)?,
    ) {
        val onDismissAction: () -> Unit = {
            onDismiss?.invoke()
            clearSnackbar()
        }
        val snackBarHost =
            AlertEvent.SnackBarHost(
                message = message,
                actionLabel = actionLabel,
                duration = duration,
                alertType = alertType,
                containerColor = containerColor,
                contentColor = contentColor,
                actionColor = actionColor,
                actionContentColor = actionContentColor,
                onDismiss = onDismissAction,
                onAction = onAction,
            )
        snackbarStateFlow.tryEmit(snackBarHost)
    }

    override fun showCircularProgressIndicator(
        message: String?,
        textPosition: ProgressTextPosition,
    ) {
        dialogStateFlow.tryEmit(
            AlertEvent.DialogAlert.CircularIndicator(
                text = message,
                textPosition = textPosition,
            ),
        )
    }

    override fun showLinearProgressIndicator(
        message: String?,
        progress: Float?,
    ) {
        dialogStateFlow.tryEmit(
            AlertEvent.DialogAlert.LinearIndicator(
                message = message,
                progress = progress,
            ),
        )
    }

//    override fun toast(message: String) {
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }

    override fun hide() {
        dialogStateFlow.value = null
    }

    override fun clearSnackbar() {
        snackbarStateFlow.value = null
    }
}
