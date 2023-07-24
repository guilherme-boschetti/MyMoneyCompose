package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R

@Composable
fun ExtendedFab(
    onClick: () -> Unit = {},
    @DrawableRes iconResource: Int,
    text: String = ""
) {
    /*ExtendedFloatingActionButton(onClick = onClick,
        backgroundColor = Color.White,
        text = {
            Text(
                text = text.uppercase(),
                fontSize = 16.sp,
                color = Color.Red
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconResource),
                contentDescription = text,
                tint = Color.Red
            )
        }
    )*/

    Button(
        modifier = Modifier.height(dimensionResource(R.dimen.button_height)),
        onClick = onClick,
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        border = BorderStroke(2.dp, Color.Red),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
            pressedElevation = 12.dp,
            disabledElevation = 2.dp,
            hoveredElevation = 8.dp,
            focusedElevation = 8.dp
        )
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = iconResource),
            contentDescription = text,
            tint = Color.Red
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text.uppercase(),
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}