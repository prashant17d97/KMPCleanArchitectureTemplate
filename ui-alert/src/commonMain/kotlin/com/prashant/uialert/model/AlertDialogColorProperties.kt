package com.prashant.uialert.model

import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class AlertDialogColorProperties(
    val dialogContainerColor: Color = Color.Unspecified,
    val dialogContentColor: Color = Color.Unspecified,
    val dialogTitleContentColor: Color = Color.Unspecified,
    val dialogTextContentColor: Color = Color.Unspecified,
) {
    val containerColor: Color
        @Composable
        get() =
            if (this.dialogContainerColor != Color.Unspecified) {
                this.dialogContainerColor
            } else {
                AlertDialogDefaults.containerColor
            }

    val iconContentColor: Color
        @Composable
        get() =
            if (this.dialogContentColor != Color.Unspecified) {
                this.dialogContentColor
            } else {
                AlertDialogDefaults.iconContentColor
            }
    val titleContentColor: Color
        @Composable
        get() =
            if (this.dialogTitleContentColor != Color.Unspecified) {
                this.dialogTitleContentColor
            } else {
                AlertDialogDefaults.titleContentColor
            }
    val textContentColor: Color
        @Composable
        get() = if (this.dialogTextContentColor != Color.Unspecified) this.dialogTextContentColor else AlertDialogDefaults.textContentColor
}
