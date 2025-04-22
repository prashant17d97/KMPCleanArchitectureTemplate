package com.prashant.uialert.api

import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import app.debugdesk.uialerts.event.ProgressTextPosition
import com.prashant.uialert.event.AlertEvent
import com.prashant.uialert.event.AlertType
import com.prashant.uialert.model.AlertDialogColorProperties
import kmpcleanarchitecturetemplate.ui_alert.generated.resources.Res
import kmpcleanarchitecturetemplate.ui_alert.generated.resources.cancel
import kmpcleanarchitecturetemplate.ui_alert.generated.resources.ok
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

/**
 * Interface for dispatching alerts in the UI.
 * Provides methods to show and hide alerts such as SnackBars and Dialogs.
 */
interface AlertDispatcher {
    /**
     * A [StateFlow] emitting the current [AlertEvent.SnackBarHost] to be shown.
     * Emits null if no SnackBar is currently being shown.
     */
    val snackBarHost: StateFlow<AlertEvent.SnackBarHost?>

    /**
     * A [StateFlow] emitting the current [AlertEvent.DialogAlert] to be shown.
     * Emits null if no Dialog is currently being shown.
     */
    val dialog: StateFlow<AlertEvent.DialogAlert?>

    /**
     * Displays an alert dialog with the specified parameters.
     *
     * @param dialogIcon Optional resource ID for the dialog icon.
     * @param title Title text for the dialog.
     * @param message Message content for the dialog.
     * @param alertType Type of alert; defaults to [AlertType.INFO].
     * @param shape Shape of the dialog; defaults to [ShapeDefaults.Medium].
     * @param dismissOnBackPress Whether the dialog dismisses on back press; defaults to true.
     * @param dismissOnClickOutside Whether the dialog dismisses when clicking outside; defaults to true.
     * @param tonalElevation Elevation of the dialog; defaults to [Dp.Hairline].
     * @param confirmButtonText Resource ID for the confirm button text; defaults to R.string.ok.
     * @param cancelButtonText Resource ID for the cancel button text; defaults to R.string.cancel.
     * @param dialogColorProperties Color properties for the dialog; defaults to [AlertDialogColorProperties()].
     * @param onConfirm Action to perform on confirm; defaults to an empty lambda.
     * @param onCancel Action to perform on cancel; defaults to an empty lambda.
     * @param hideOnConfirm Whether to hide the dialog on confirm; defaults to true.
     */
    fun showAlertDialog(
        dialogIcon: DrawableResource? = null,
        title: String,
        message: String,
        alertType: AlertType = AlertType.INFO,
        shape: Shape = ShapeDefaults.Medium,
        dismissOnBackPress: Boolean = true,
        dismissOnClickOutside: Boolean = true,
        tonalElevation: Dp = Dp.Hairline,
        confirmButtonText: StringResource = Res.string.ok,
        cancelButtonText: StringResource = Res.string.cancel,
        dialogColorProperties: AlertDialogColorProperties = AlertDialogColorProperties(),
        onConfirm: () -> Unit = {},
        onCancel: () -> Unit = {},
        hideOnConfirm: Boolean = true,
    )

    /**
     * Displays a SnackBar with the specified message and optional action.
     *
     * @param message The message to display in the SnackBar.
     * @param actionLabel Optional label for the action button.
     * @param duration Duration for which the SnackBar should be shown; defaults to [SnackbarDuration.Short].
     * @param alertType Type of alert; defaults to [AlertType.INFO].
     * @param containerColor Background color of the SnackBar; defaults to [Color.Unspecified].
     * @param contentColor Color of the message text; defaults to [Color.Unspecified].
     * @param actionColor Color of the action button; defaults to [Color.Unspecified].
     * @param actionContentColor Color of the action button text; defaults to [Color.Unspecified].
     * @param onDismiss Action to perform when the SnackBar is dismissed; defaults to null.
     * @param onAction Action to perform when the action button is clicked; defaults to null.
     */
    fun showSnackBar(
        message: String? = null,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short,
        alertType: AlertType = AlertType.INFO,
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        actionColor: Color = Color.Unspecified,
        actionContentColor: Color = Color.Unspecified,
        onDismiss: (() -> Unit)? = null,
        onAction: (() -> Unit)? = null,
    )

    /**
     * Displays a circular progress indicator with optional text.
     *
     * @param message Optional text to display alongside the progress indicator.
     * @param textPosition Position of the text relative to the progress indicator; defaults to [ProgressTextPosition.BOTTOM].
     */
    fun showCircularProgressIndicator(
        message: String? = null,
        textPosition: ProgressTextPosition = ProgressTextPosition.BOTTOM,
    )

    /**
     * Displays a linear progress indicator with optional message and progress value.
     *
     * @param message Optional message to display alongside the progress indicator.
     * @param progress Optional progress value (0.0 to 1.0); if null, an indeterminate progress indicator is shown.
     */
    fun showLinearProgressIndicator(
        message: String? = null,
        progress: Float? = null,
    )

//    /**
//     * Displays a Toast message with the specified content.
//     *
//     * @param message The message to display in the Toast.
//     */
//    fun toast(message: String)

    /**
     * Hides the currently displayed SnackBar or Dialog.
     * Can be used to dismiss any visible alerts.
     */
    fun hide()

    /**
     * Clears the currently displayed SnackBar.
     */
    fun clearSnackbar()
}
