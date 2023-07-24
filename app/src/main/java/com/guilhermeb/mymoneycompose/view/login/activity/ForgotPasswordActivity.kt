package com.guilhermeb.mymoneycompose.view.login.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.constant.Constants
import com.guilhermeb.mymoneycompose.view.app.activity.AbstractActivity
import com.guilhermeb.mymoneycompose.view.login.screen.ForgotPasswordScreen
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var email: String = ""

        val intentActivity = intent
        if (intentActivity.hasExtra(Constants.INTENT_EXTRA_KEY_EMAIL)) {
            email = intentActivity.getStringExtra(Constants.INTENT_EXTRA_KEY_EMAIL) ?: ""
        }

        setContent {
            MyMoneyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.app_screen_background)
                ) {
                    ForgotPasswordScreen(email, this)
                }
            }
        }
    }
}