package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.guilhermeb.mymoneycompose.view.ui.theme.ColorPrimary

@Composable
fun AppCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onCheckedChange: (_checked: Boolean) -> Unit = {},
    text: String = "",
    enabled: Boolean = true
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(checkedColor = ColorPrimary),
            enabled = enabled
        )
        Text(
            text = text
        )
    }
}