package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun TextWithShadow(
    text: String,
    textColor: Color,
    shadowColor: Color,
    textSize: TextUnit
) {
    Box {
        Text(
            text = text,
            color = shadowColor,
            fontSize = textSize,
            modifier = Modifier
                .offset(
                    x = 2.dp,
                    y = 2.dp
                )
                .alpha(0.75f)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = textSize
        )
    }
}