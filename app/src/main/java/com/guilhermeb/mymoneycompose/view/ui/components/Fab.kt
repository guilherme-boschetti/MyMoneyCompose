package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun Fab(onClick: () -> Unit = {},
    @DrawableRes iconResource: Int,
    contentDescriptionText: String = ""
) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResource),
            contentDescription = contentDescriptionText
        )
    }
}