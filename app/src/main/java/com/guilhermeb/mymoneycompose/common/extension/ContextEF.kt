package com.guilhermeb.mymoneycompose.common.extension

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.guilhermeb.mymoneycompose.common.constant.Constants
import com.guilhermeb.mymoneycompose.common.helper.SharedPreferencesHelper
import com.guilhermeb.mymoneycompose.common.helper.getSharedPreferencesKey

// ========== ========== Context Extension Functions ========== ==========

fun Context.getAndroidTextColorPrimary(): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(android.R.attr.textColorPrimary, typedValue, true)
    val typedArray =
        theme.obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.textColorPrimary))
    return typedArray.getColor(0, -1)
}

fun Context.isUiModeNightActive(): Boolean {
    val nightMode: String? =
        SharedPreferencesHelper.getInstance()
            .getValue(getSharedPreferencesKey(Constants.NIGHT_MODE), Constants.FOLLOW_SYSTEM)
    if (nightMode != null) {
        when (nightMode) {
            Constants.NO -> return false
            Constants.YES -> return true
        }
    }
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO -> return false // Night mode is not active, we're using the light theme
        Configuration.UI_MODE_NIGHT_YES -> return true // Night mode is active, we're using dark theme
    }
    return false
}

fun Context.showConfirmationDialog(@StringRes messageId: Int, positiveAction: () -> Any) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)

    builder.setMessage(messageId)
        .setCancelable(false)
        .setPositiveButton(com.guilhermeb.mymoneycompose.R.string.yes) { _, _ -> positiveAction() }
        .setNegativeButton(com.guilhermeb.mymoneycompose.R.string.no) { dialog, _ -> dialog.dismiss() }

    val alertDialog: AlertDialog = builder.create()
    alertDialog.show()
}

fun Context.showInformationDialog(@StringRes messageId: Int) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)

    builder.setMessage(messageId)
        .setCancelable(false)
        .setPositiveButton(com.guilhermeb.mymoneycompose.R.string.ok) { dialog, _ -> dialog.dismiss() }

    val alertDialog: AlertDialog = builder.create()
    alertDialog.show()
}