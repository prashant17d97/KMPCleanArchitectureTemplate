package com.prashant.uialert.host

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.prashant.core.ui.theme.AppTheme
import com.prashant.core.ui.uicomponents.CoreButton
import com.prashant.core.utils.CoreUtils.icon
import com.prashant.core.utils.CoreUtils.value
import com.prashant.uialert.event.AlertEvent
import com.prashant.uialert.utils.AlertUtils.dialogButtonColor
import com.prashant.uialert.utils.AlertUtils.dialogButtonContentColor
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Composable function to display an AlertDialog.
 *
 * @param modifier Modifier to be applied to the AlertDialog.
 * @param alertDialog The AlertEvent.Dialog object containing the dialog's properties.
 */

@Composable
fun AlertAndIndicator(
    modifier: Modifier = Modifier,
    alertDialog: AlertEvent.DialogAlert?,
) {
    AnimatedVisibility(alertDialog != null) {
        if (alertDialog == null) return@AnimatedVisibility
        when (alertDialog) {
            is AlertEvent.DialogAlert.Dialog -> {
                AlertDialog(
                    modifier = modifier,
                    alertDialog = alertDialog,
                )
            }

            is AlertEvent.DialogAlert.CircularIndicator -> {
                CustomCircularProgressIndicator(circularIndicator = alertDialog)
            }

            is AlertEvent.DialogAlert.LinearIndicator -> {
                CustomLinearProgressIndicator(
                    progress = alertDialog.progress,
                    text = alertDialog.message,
                )
            }
        }
    }
}

@Composable
private fun AlertDialog(
    modifier: Modifier = Modifier,
    alertDialog: AlertEvent.DialogAlert.Dialog?,
) {
    AnimatedVisibility(alertDialog != null) {
        if (alertDialog == null) return@AnimatedVisibility
        AlertDialog(
            modifier = modifier,
            onDismissRequest = alertDialog.onCancel,
            confirmButton = {
                CoreButton(
                    label = alertDialog.confirmButtonText.value,
                    onClick = alertDialog.onConfirm,
                    containerColor = alertDialog.alertType.dialogButtonColor,
                    contentColor = alertDialog.alertType.dialogButtonContentColor,
                    disabledContainerColor = alertDialog.alertType.dialogButtonColor.copy(alpha = 0.12f),
                    disabledContentColor = alertDialog.alertType.dialogButtonContentColor.copy(alpha = 0.40f),
                )
            },
            dismissButton = {
                CoreButton(
                    label = alertDialog.cancelButtonText.value,
                    onClick = alertDialog.onCancel,
                    containerColor = alertDialog.alertType.dialogButtonColor,
                    contentColor = alertDialog.alertType.dialogButtonContentColor,
                    disabledContainerColor = alertDialog.alertType.dialogButtonColor.copy(alpha = 0.12f),
                    disabledContentColor = alertDialog.alertType.dialogButtonContentColor.copy(alpha = 0.40f),
                )
            },
            icon =
                alertDialog.dialogIcon?.let {
                    {
                        Icon(
                            painter = it.icon,
                            contentDescription = "Alert ${alertDialog.alertType} Icons",
                        )
                    }
                },
            title = { Text(alertDialog.title) },
            text = { Text(alertDialog.message) },
            shape = alertDialog.shape,
            containerColor = alertDialog.dialogColorProperties.containerColor,
            iconContentColor = alertDialog.dialogColorProperties.iconContentColor,
            titleContentColor = alertDialog.dialogColorProperties.titleContentColor,
            textContentColor = alertDialog.dialogColorProperties.textContentColor,
            tonalElevation = alertDialog.tonalElevation,
            properties =
                DialogProperties(
                    dismissOnBackPress = alertDialog.dismissOnBackPress,
                    dismissOnClickOutside = alertDialog.dismissOnClickOutside,
                ),
        )
    }
}

@Preview
@Composable
private fun AlertDialogPreview() {
    AppTheme {
        AlertDialog(alertDialog = AlertEvent.DialogAlert.Dialog(title = "title", message = "Message"))
    }
}
