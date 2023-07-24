package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermeb.mymoneycompose.R

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    value: String = "",
    contentDescriptionText: String = "",
    errorMessage: State<String?> = mutableStateOf(""),
    onTextChanged: (text: String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = {
        if ((errorMessage.value ?: "").isNotEmpty())
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_baseline_error_24),
                stringResource(R.string.error),
                tint = MaterialTheme.colors.error
            )
    },
    textArea: Boolean = false,
    enabled: Boolean = true
) {
    var modifierLocal = modifier
    var singleline = true

    if (textArea) {
        modifierLocal = modifierLocal.height(dimensionResource(R.dimen.text_area_height))
        singleline = false
    }

    OutlinedTextField(modifier = modifierLocal.fillMaxWidth()
        .semantics { contentDescription = contentDescriptionText },
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        value = value,
        singleLine = singleline,
        isError = (errorMessage.value ?: "").isNotEmpty(),
        onValueChange = onTextChanged,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
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

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    value: TextFieldValue = TextFieldValue(text = ""),
    contentDescriptionText: String = "",
    errorMessage: State<String?> = mutableStateOf(""),
    onTextChanged: (text: TextFieldValue) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable (() -> Unit)? = {
        if ((errorMessage.value ?: "").isNotEmpty())
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_baseline_error_24),
                stringResource(R.string.error),
                tint = MaterialTheme.colors.error
            )
    },
    textArea: Boolean = false,
    enabled: Boolean = true
) {
    var modifierLocal = modifier
    var singleline = true

    if (textArea) {
        modifierLocal = modifierLocal.height(dimensionResource(R.dimen.text_area_height))
        singleline = false
    }

    OutlinedTextField(modifier = modifierLocal.fillMaxWidth()
        .semantics { contentDescription = contentDescriptionText },
        label = { Text(text = label) },
        placeholder = {
            Text(
                text = placeholder
            )
        },
        value = value,
        singleLine = singleline,
        isError = (errorMessage.value ?: "").isNotEmpty(),
        onValueChange = onTextChanged,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
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