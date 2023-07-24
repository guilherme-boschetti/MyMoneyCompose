package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R

@Composable
fun MyMoneyLogoAndNameVertical() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.my_money_logo),
            contentDescription = stringResource(R.string.image_logo),
            modifier = Modifier.size(width = 136.dp, height = 136.dp)
                .background(
                    brush = Brush.radialGradient(
                        radius = 200f,
                        colors = listOf(
                            colorResource(R.color.colorPrimary),
                            colorResource(android.R.color.transparent),
                            colorResource(android.R.color.transparent)
                        )
                    )
                ).padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextWithShadow(
            text = stringResource(R.string.app_name),
            textColor = colorResource(R.color.yellow_logo),
            shadowColor = colorResource(R.color.colorPrimaryDark),
            textSize = 32.sp
        )
    }
}