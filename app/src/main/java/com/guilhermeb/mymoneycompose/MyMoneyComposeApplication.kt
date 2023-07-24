package com.guilhermeb.mymoneycompose

import android.app.Application
import com.guilhermeb.mymoneycompose.viewmodel.authentication.AuthenticationViewModel
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyMoneyComposeApplication : Application() {

    @Inject
    lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        @Volatile
        private var INSTANCE: MyMoneyComposeApplication? = null

        @Synchronized
        fun getInstance(): MyMoneyComposeApplication {
            if (INSTANCE == null)
                throw IllegalStateException("Configure the application on AndroidManifest.xml")
            return INSTANCE!!
        }
    }

    fun getCurrentUserEmail(): String? = authenticationViewModel.getCurrentUserEmail()
}