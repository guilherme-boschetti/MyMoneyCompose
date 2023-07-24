package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.guilhermeb.mymoneycompose.view.ui.theme.ColorPrimary

@Composable
fun AppRadioButton(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    text: String = "",
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(selectedColor = ColorPrimary),
            enabled = enabled
        )
        Text(
            text = text
        )
    }
}