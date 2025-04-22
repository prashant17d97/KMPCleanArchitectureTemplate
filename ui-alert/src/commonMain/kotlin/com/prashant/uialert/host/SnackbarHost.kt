package com.prashant.uialert.host

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.prashant.uialert.event.AlertEvent
import com.prashant.uialert.utils.AlertUtils.value
import com.prashant.uialert.utils.AlertUtils.valueContent

@Composable
fun SnackbarHost(
    modifier: Modifier = Modifier,
    snackBarHost: AlertEvent.SnackBarHost?,
    snackbarHostState: SnackbarHostState,
) {
    if (snackBarHost == null) return

    LaunchedEffect(snackBarHost.message) {
        if (snackBarHost.message == null) return@LaunchedEffect
        val result =
            snackbarHostState.showSnackbar(
                message = snackBarHost.message,
                actionLabel = snackBarHost.actionLabel,
                duration = snackBarHost.duration,
            )

        when (result) {
            SnackbarResult.Dismissed -> {
                snackBarHost.onDismiss?.invoke()
            }

            SnackbarResult.ActionPerformed -> {
                snackBarHost.onAction?.invoke()
            }
        }
    }

    SnackbarHost(
        hostState = snackbarHostState,
        modifier = modifier,
        snackbar = { data ->
            Snackbar(
                snackbarData = data,
                containerColor = snackBarHost.containerColor.value(snackBarHost.alertType),
                contentColor = snackBarHost.contentColor.valueContent(snackBarHost.alertType),
                actionColor = snackBarHost.actionColor.value(snackBarHost.alertType),
                actionContentColor = snackBarHost.actionContentColor.valueContent(snackBarHost.alertType),
            )
        },
    )
}
