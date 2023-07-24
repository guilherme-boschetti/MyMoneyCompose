package com.guilhermeb.mymoneycompose.view.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(
    showPicker: MutableState<Boolean> = remember { mutableStateOf(false) },
    datePickerResult: DatePickerResult
) {
    val datePickerState = rememberDatePickerState()
    val confirmEnabled = remember { derivedStateOf { datePickerState.selectedDateMillis != null } }

    DatePickerDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
            showPicker.value = false
        },
        confirmButton = {
            androidx.compose.material.TextButton(
                onClick = {
                    datePickerResult.onDatePick(datePickerState.selectedDateMillis)
                    showPicker.value = false
                },
                enabled = confirmEnabled.value
            ) {
                androidx.compose.material.Text("OK")
            }
        },
        dismissButton = {
            androidx.compose.material.TextButton(
                onClick = {
                    showPicker.value = false
                }
            ) {
                androidx.compose.material.Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

interface DatePickerResult {
    fun onDatePick(selectedDateMillis: Long?)
}