package com.guilhermeb.mymoneycompose.common.util

import androidx.appcompat.app.AppCompatDelegate
import com.guilhermeb.mymoneycompose.BuildConfig
import com.guilhermeb.mymoneycompose.MyMoneyComposeApplication
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.constant.Constants
import com.guilhermeb.mymoneycompose.common.helper.SharedPreferencesHelper
import com.guilhermeb.mymoneycompose.common.helper.getSharedPreferencesKey

fun getAppVersion(): String {
    return MyMoneyComposeApplication.getInstance().applicationContext.getString(
        R.string.version,
        BuildConfig.VERSION_NAME
    )
}

fun configNightMode() {
    val nightMode: String? =
        SharedPreferencesHelper.getInstance()
            .getValue(getSharedPreferencesKey(Constants.NIGHT_MODE), Constants.FOLLOW_SYSTEM)
    if (nightMode != null) {
        when (nightMode) {
            Constants.NO -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Constants.YES -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}