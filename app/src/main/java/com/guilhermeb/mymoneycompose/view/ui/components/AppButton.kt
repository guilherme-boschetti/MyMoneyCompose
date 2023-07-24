package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    label: String = "",
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        modifier = modifier.fillMaxWidth()
            .height(dimensionResource(R.dimen.button_height)),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = label,
            fontSize = 16.sp
        )
    }
}