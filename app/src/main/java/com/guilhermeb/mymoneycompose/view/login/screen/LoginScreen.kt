package com.guilhermeb.mymoneycompose.view.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.view.login.activity.LoginInterface
import com.guilhermeb.mymoneycompose.view.ui.components.AppButton
import com.guilhermeb.mymoneycompose.view.ui.components.InputTextField
import com.guilhermeb.mymoneycompose.view.ui.components.MyMoneyLogoAndNameVertical
import com.guilhermeb.mymoneycompose.view.ui.components.PasswordTextField
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme

@Composable
fun LoginScreen(
    loginInterface: LoginInterface,
    isEmailError: MutableLiveData<String>,
    isPasswordError: MutableLiveData<String>
) {

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val emailError = isEmailError.observeAsState()
    val passwordError = isPasswordError.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.app_screen_background))
            .padding(
                dimensionResource(R.dimen.form_side_margin),
                dimensionResource(R.dimen.screen_margin),
                dimensionResource(R.dimen.form_side_margin),
                dimensionResource(R.dimen.screen_margin)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MyMoneyLogoAndNameVertical()

        Spacer(modifier = Modifier.height(28.dp))

        InputTextField(
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.hint_email),
            value = email.value,
            errorMessage = emailError,
            onTextChanged = {
                email.value = it
                isEmailError.value = ""
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordTextField(
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.hint_password),
            value = password.value,
            errorMessage = passwordError,
            onTextChanged = {
                password.value = it
                isPasswordError.value = ""
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                loginInterface.signIn(email.value, password.value)
            })
        )

        Spacer(modifier = Modifier.height(28.dp))

        AppButton(label = stringResource(R.string.login),
            onClick = {
                loginInterface.signIn(email.value, password.value)
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        TextButton(onClick = {
            loginInterface.goToForgotPasswordActivity(email.value)
        }) {
            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 16.sp,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(R.color.colorPrimary),
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(28.dp).weight(1f))

        Text(
            text = stringResource(R.string.do_not_have_an_account),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        AppButton(label = stringResource(R.string.create_account),
            onClick = {
                loginInterface.goToCreateAccountActivity(email.value, password.value)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val mock = object : LoginInterface {
        override fun signIn(email: String, password: String) {
            // do nothing
        }

        override fun goToForgotPasswordActivity(email: String) {
            // do nothing
        }

        override fun goToCreateAccountActivity(email: String, password: String) {
            // do nothing
        }
    }

    val isEmailError = MutableLiveData("")
    val isPasswordError = MutableLiveData("")

    MyMoneyComposeTheme {
        LoginScreen(mock, isEmailError, isPasswordError)
    }
}