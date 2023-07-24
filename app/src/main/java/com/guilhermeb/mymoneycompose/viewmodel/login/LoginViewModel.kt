package com.guilhermeb.mymoneycompose.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.constant.Constants
import com.guilhermeb.mymoneycompose.common.cryptography.md5
import com.guilhermeb.mymoneycompose.common.helper.DataStorePreferencesHelper
import com.guilhermeb.mymoneycompose.common.helper.getPreferencesDataStoreKey
import com.guilhermeb.mymoneycompose.model.repository.contract.AsyncProcess
import com.guilhermeb.mymoneycompose.viewmodel.authentication.AuthenticationViewModel
import com.guilhermeb.mymoneycompose.viewmodel.login.state.LoginFormState
import com.guilhermeb.mymoneycompose.common.validation.isEmailValid
import com.guilhermeb.mymoneycompose.common.validation.isPasswordValid
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val authenticationViewModel: AuthenticationViewModel) :
    ViewModel() {

    private val _loginFormState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginFormState

    fun loginFormDataChanged(email: String, password: String) {
        isLoginFormDataValid(email, password)
    }

    fun isLoginFormDataValid(email: String, password: String): Boolean {
        if (!isEmailValid(email)) {
            _loginFormState.value = LoginFormState(emailError = R.string.invalid_email)
        } else if (!isPasswordValid(password)) {
            _loginFormState.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginFormState.value = LoginFormState(isDataValid = true)
            return true
        }
        return false
    }

    fun getCurrentUserEmail(): String? {
        return authenticationViewModel.getCurrentUserEmail()
    }

    fun signIn(email: String, password: String, asyncProcess: AsyncProcess<String?>) {
        authenticationViewModel.signIn(email, password, object : AsyncProcess<String?> {
            override fun onComplete(isSuccessful: Boolean, result: String?) {
                DataStorePreferencesHelper.getInstance().saveStringIntoDataStorePreferences(
                    getPreferencesDataStoreKey(Constants.PASSWORD),
                    md5(password)
                )
                asyncProcess.onComplete(isSuccessful, result)
            }
        })
    }
}