package com.guilhermeb.mymoneycompose.view.login.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.view.ui.components.*
import com.guilhermeb.mymoneycompose.view.ui.theme.ColorPrimary
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme

@Composable
fun CreateAccountScreen(email: String, password: String, actionBack: DefaultActionBarInterface) {

    val checked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.app_screen_background)),
    ) {
        DefaultActionBar(stringResource(R.string.create_account), actionBack)

        Column(
            modifier = Modifier.fillMaxSize().weight(1f)
                .padding(
                    dimensionResource(R.dimen.form_side_margin),
                    dimensionResource(R.dimen.screen_margin),
                    dimensionResource(R.dimen.form_side_margin),
                    dimensionResource(R.dimen.screen_margin)
                )
        ) {

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

            Spacer(modifier = Modifier.height(10.dp))

            InputTextField(
                label = stringResource(R.string.repeat_the_email),
                placeholder = stringResource(R.string.hint_email),
                value = "",
                errorMessage = remember { mutableStateOf("") },
                onTextChanged = {
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
                value = password,
                errorMessage = remember { mutableStateOf("") },
                onTextChanged = {
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            PasswordTextField(
                label = stringResource(R.string.repeat_the_password),
                placeholder = stringResource(R.string.hint_password),
                value = "",
                errorMessage = remember { mutableStateOf("") },
                onTextChanged = {
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { _checked ->
                        checked.value = _checked
                    },
                    colors = CheckboxDefaults.colors(checkedColor = ColorPrimary)
                )

                Text(
                    text = stringResource(R.string.i_accept_the)
                )

                TextButton(onClick = {
                    println("terms_and_conditions !!!")
                }) {
                    Text(
                        text = stringResource(R.string.terms_and_conditions_underlined),
                        fontSize = 16.sp,
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            color = colorResource(R.color.colorPrimary),
                            fontSize = 16.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp).weight(1f))

            AppButton(label = stringResource(R.string.create_account),
                onClick = {
                    println("create_account !!!")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateAccountScreenPreview() {
    val mock = object : DefaultActionBarInterface {
        override fun actionBack() {
            // do nothing
        }
    }

    MyMoneyComposeTheme {
        CreateAccountScreen("", "", mock)
    }
}