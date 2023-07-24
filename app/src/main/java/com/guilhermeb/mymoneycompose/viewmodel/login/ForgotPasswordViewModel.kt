package com.guilhermeb.mymoneycompose.viewmodel.login

import androidx.lifecycle.ViewModel
import com.guilhermeb.mymoneycompose.model.repository.contract.AsyncProcess
import com.guilhermeb.mymoneycompose.viewmodel.authentication.AuthenticationViewModel
import com.guilhermeb.mymoneycompose.common.validation.isEmailValid
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(private val authenticationViewModel: AuthenticationViewModel) :
    ViewModel() {

    fun isFormDataValid(email: String): Boolean {
        return isEmailValid(email)
    }

    fun sendPasswordResetEmail(email: String, asyncProcess: AsyncProcess<String?>) {
        authenticationViewModel.sendPasswordResetEmail(email, asyncProcess)
    }
}