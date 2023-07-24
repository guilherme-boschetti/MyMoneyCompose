package com.guilhermeb.mymoneycompose.view.app.offline.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.view.app.activity.AbstractActivity
import com.guilhermeb.mymoneycompose.view.app.offline.screen.OfflineScreen
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme

class OfflineActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMoneyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.app_screen_background)
                ) {
                    OfflineScreen(this)
                }
            }
        }
    }
}