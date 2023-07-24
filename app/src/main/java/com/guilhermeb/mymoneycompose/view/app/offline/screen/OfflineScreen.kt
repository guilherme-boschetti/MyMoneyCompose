package com.guilhermeb.mymoneycompose.view.app.offline.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.view.ui.components.DefaultActionBar
import com.guilhermeb.mymoneycompose.view.ui.components.DefaultActionBarInterface

@Composable
fun OfflineScreen(actionBack: DefaultActionBarInterface) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = colorResource(R.color.app_screen_background))
    ) {
        DefaultActionBar(stringResource(R.string.app_name), actionBack)

        Column(
            modifier = Modifier.fillMaxSize().weight(1f)
                .padding(dimensionResource(R.dimen.screen_margin)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_wifi_off_136),
                contentDescription = stringResource(R.string.image_logo)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.no_internet_connection),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.please_check_your_internet_connection_and_try_again),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}