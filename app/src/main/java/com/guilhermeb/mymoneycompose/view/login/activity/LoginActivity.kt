package com.guilhermeb.mymoneycompose.view.login.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.constant.Constants
import com.guilhermeb.mymoneycompose.common.util.isNetworkAvailable
import com.guilhermeb.mymoneycompose.common.util.showToast
import com.guilhermeb.mymoneycompose.model.repository.contract.AsyncProcess
import com.guilhermeb.mymoneycompose.view.app.activity.AbstractActivity
import com.guilhermeb.mymoneycompose.view.app.offline.activity.OfflineActivity
import com.guilhermeb.mymoneycompose.view.login.screen.LoginScreen
import com.guilhermeb.mymoneycompose.view.money.activity.MoneyHostActivity
import com.guilhermeb.mymoneycompose.view.ui.theme.MyMoneyComposeTheme
import com.guilhermeb.mymoneycompose.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AbstractActivity(), LoginInterface {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private val isEmailError = MutableLiveData("")
    private val isPasswordError = MutableLiveData("")

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Handle the splash screen transition.
        super.onCreate(savedInstanceState)
        setContent {
            MyMoneyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.app_screen_background)
                ) {
                    LoginScreen(this, isEmailError, isPasswordError)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in
        val currentUserEmail = loginViewModel.getCurrentUserEmail()
        if (currentUserEmail != null) {
            configNightModeAndGoToMoneyHostActivity(fetchDataFromFirebaseRTDB = false)
        }
    }

    private fun configNightModeAndGoToMoneyHostActivity(fetchDataFromFirebaseRTDB: Boolean) {
        //configNightMode()
        goToMoneyHostActivity(fetchDataFromFirebaseRTDB)
    }

    private fun goToMoneyHostActivity(fetchDataFromFirebaseRTDB: Boolean) {
        val intent = Intent(this, MoneyHostActivity::class.java)
        intent.putExtra(
            Constants.INTENT_EXTRA_KEY_FETCH_DATA_FROM_FIREBASE_RTDB,
            fetchDataFromFirebaseRTDB
        )
        startActivity(intent)
        finish()
    }

    override fun signIn(email: String, password: String) {
        if (email.isEmpty()) {
            isEmailError.value = getString(R.string.invalid_email)
            return
        }
        if (password.isEmpty()) {
            isPasswordError.value = getString(R.string.invalid_password)
            return
        }
        if (isNetworkAvailable()) {
            progressDialog.show()
            loginViewModel.signIn(email, password, object : AsyncProcess<String?> {
                override fun onComplete(isSuccessful: Boolean, result: String?) {
                    progressDialog.dismiss()
                    if (isSuccessful) {
                        configNightModeAndGoToMoneyHostActivity(fetchDataFromFirebaseRTDB = true)
                    } else {
                        val message = result ?: getString(R.string.failed_to_login)
                        showToast(this@LoginActivity, message)
                        if (result.equals(getString(R.string.invalid_account)) ||
                            result.equals(getString(R.string.invalid_email))
                        ) {
                            isEmailError.value = result
                        } else if (result.equals(getString(R.string.invalid_password))) {
                            isPasswordError.value = result
                        }
                    }
                }
            })
        } else {
            val intent = Intent(this, OfflineActivity::class.java)
            startActivity(intent)
        }
    }

    override fun goToForgotPasswordActivity(email: String) {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        if (email.isNotEmpty()) {
            intent.putExtra(Constants.INTENT_EXTRA_KEY_EMAIL, email)
        }
        startActivity(intent)
    }

    override fun goToCreateAccountActivity(email: String, password: String) {
        val intent = Intent(this, CreateAccountActivity::class.java)
        if (email.isNotEmpty()) {
            intent.putExtra(Constants.INTENT_EXTRA_KEY_EMAIL, email)
        }
        if (password.isNotEmpty()) {
            intent.putExtra(Constants.INTENT_EXTRA_KEY_EMAIL, email)
        }
        startActivity(intent)
    }
}

interface LoginInterface {
    fun signIn(email: String, password: String)
    fun goToForgotPasswordActivity(email: String)
    fun goToCreateAccountActivity(email: String, password: String)
}