package com.prashant.uialert.host

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import app.debugdesk.uialerts.event.ProgressTextPosition
import com.prashant.uialert.event.AlertEvent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomCircularProgressIndicator(
    modifier: Modifier = Modifier,
    circularIndicator: AlertEvent.DialogAlert.CircularIndicator,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = 6.dp,
    cap: StrokeCap = StrokeCap.Round,
    textSize: Dp = 16.sp.value.dp,
) {
    val showText = !circularIndicator.text.isNullOrBlank()
    val indicatorModifier = modifier.padding(16.dp)
    Dialog {
        val indicator = @Composable {
            CircularProgressIndicator(
                color = color,
                strokeWidth = strokeWidth,
                modifier = Modifier.size(48.dp),
                strokeCap = cap,
            )
        }

        val textComposable = @Composable {
            if (showText) {
                Text(
                    text = circularIndicator.text.orEmpty(),
                    fontSize = textSize.value.sp,
                    modifier = Modifier.padding(4.dp),
                )
            }
        }
        val verticalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterVertically)
        val horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)

        when (circularIndicator.textPosition) {
            ProgressTextPosition.TOP ->
                Column(
                    indicatorModifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = verticalArrangement,
                ) {
                    textComposable()
                    indicator()
                }

            ProgressTextPosition.BOTTOM ->
                Column(
                    indicatorModifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = verticalArrangement,
                ) {
                    indicator()
                    textComposable()
                }

            ProgressTextPosition.LEFT ->
                Row(
                    indicatorModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = horizontalArrangement,
                ) {
                    textComposable()
                    indicator()
                }

            ProgressTextPosition.RIGHT ->
                Row(
                    indicatorModifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = horizontalArrangement,
                ) {
                    indicator()
                    textComposable()
                }
        }
    }
}

@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = Color.LightGray,
    text: String? = "Loading...",
    textSize: Dp = 14.sp.value.dp,
) {
    val showText = !text.isNullOrBlank()
    Dialog {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.CenterVertically,
                ),
        ) {
            if (showText) {
                Text(
                    text = text.orEmpty(),
                    fontSize = textSize.value.sp,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
            if (progress != null) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth(),
                    color = color,
                    trackColor = trackColor,
                )
            } else {
                LinearProgressIndicator(
                    color = color,
                    trackColor = trackColor,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Dialog(
        {},
        properties =
            DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
    ) {
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.extraLarge,
            color = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            tonalElevation = 4.dp,
            shadowElevation = 4.dp,
            content = content,
        )
    }
}

@Preview
@Composable
fun PreviewCircularTop() {
    CustomCircularProgressIndicator(
        circularIndicator =
            AlertEvent.DialogAlert.CircularIndicator(
                text = "Loading...",
                textPosition = ProgressTextPosition.TOP,
            ),
    )
}

@Preview
@Composable
fun PreviewCircularBottom() {
    CustomCircularProgressIndicator(
        circularIndicator =
            AlertEvent.DialogAlert.CircularIndicator(
                text = "Loading...",
                textPosition = ProgressTextPosition.BOTTOM,
            ),
    )
}

@Preview
@Composable
fun PreviewCircularLeft() {
    CustomCircularProgressIndicator(
        circularIndicator =
            AlertEvent.DialogAlert.CircularIndicator(
                text = "Loading...",
                textPosition = ProgressTextPosition.LEFT,
            ),
    )
}

@Preview
@Composable
fun PreviewCircularRight() {
    CustomCircularProgressIndicator(
        circularIndicator =
            AlertEvent.DialogAlert.CircularIndicator(
                text = "Loading...",
                textPosition = ProgressTextPosition.RIGHT,
            ),
    )
}

@Preview
@Composable
fun PreviewLinearProgress() {
    CustomLinearProgressIndicator(progress = 0.6f)
}
