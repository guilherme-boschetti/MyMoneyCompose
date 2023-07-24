package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.guilhermeb.mymoneycompose.R

@Composable
fun DefaultActionBar(title: String, action: DefaultActionBarInterface) {
    TopAppBar (
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = {
                action.actionBack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    )
}

interface DefaultActionBarInterface {
    fun actionBack()
}