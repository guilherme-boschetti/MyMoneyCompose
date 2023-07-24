package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R

/**
 * https://medium.com/google-developer-experts/how-to-create-a-composable-password-with-jetpack-compose-f1be2d48d9f0
 */
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    value: String = "",
    contentDescriptionText: String = "",
    errorMessage: State<String?> = mutableStateOf(""),
    onTextChanged: (text: String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true
) {
    val showPassword = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = contentDescriptionText },
            label = { Text(text = label) },
            placeholder = {
                Text(
                    text = placeholder
                )
            },
            value = value,
            singleLine = true,
            isError = (errorMessage.value ?: "").isNotEmpty(),
            onValueChange = onTextChanged,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = {
                if ((errorMessage.value ?: "").isNotEmpty()) {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.ic_baseline_error_24),
                        stringResource(R.string.error),
                        tint = MaterialTheme.colors.error
                    )
                    return@OutlinedTextField
                }

                val (icon, iconColor) = if (showPassword.value) {
                    Pair(
                        painterResource(R.drawable.ic_baseline_visibility_off_24),
                        colorResource(id = R.color.black_50)
                    )
                } else {
                    Pair(
                        painterResource(R.drawable.ic_baseline_visibility_24),
                        colorResource(id = R.color.black_50)
                    )
                }

                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = iconColor
                    )
                }
            },
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            enabled = enabled
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
            text = errorMessage.value ?: "",
            color = MaterialTheme.colors.error,
            fontSize = 12.sp
        )
    }
}