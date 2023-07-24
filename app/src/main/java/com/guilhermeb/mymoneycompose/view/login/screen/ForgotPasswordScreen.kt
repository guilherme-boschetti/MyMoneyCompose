package com.guilhermeb.mymoneycompose.view.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.view.ui.components.AppButton
import com.guilhermeb.mymoneycompose.view.ui.components.DefaultActionBar
import com.guilhermeb.mymoneycompose.view.ui.components.DefaultActionBarInterface
import com.guilhermeb.mymoneycompose.view.ui.components.InputTextField
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme

@Composable
fun ForgotPasswordScreen(email: String, actionBack: DefaultActionBarInterface) {

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.app_screen_background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultActionBar(stringResource(R.string.recover_password), actionBack)

        Column(
            modifier = Modifier.fillMaxSize().weight(1f)
                .padding(
                    dimensionResource(R.dimen.form_side_margin),
                    dimensionResource(R.dimen.screen_margin),
                    dimensionResource(R.dimen.form_side_margin),
                    dimensionResource(R.dimen.screen_margin)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(R.string.inform_your_e_mail_to_recover_your_password),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            InputTextField(
                label = stringResource(R.string.email),
                placeholder = stringResource(R.string.hint_email),
                value = email,
                errorMessage = remember { mutableStateOf("") },
                onTextChanged = {
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                )
            )
        }

        AppButton(modifier = Modifier.padding(
                dimensionResource(R.dimen.form_side_margin),
                dimensionResource(R.dimen.screen_margin),
                dimensionResource(R.dimen.form_side_margin),
                dimensionResource(R.dimen.screen_margin)
            ),
            label = stringResource(R.string.recover_password),
            onClick = {
                println("forgot_password !!!")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    val mock = object : DefaultActionBarInterface {
        override fun actionBack() {
            // do nothing
        }
    }

    MyMoneyComposeTheme {
        ForgotPasswordScreen("", mock)
    }
}