package com.guilhermeb.mymoneycompose.viewmodel.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.guilhermeb.mymoneycompose.R
import com.guilhermeb.mymoneycompose.common.constant.Constants
import com.guilhermeb.mymoneycompose.common.cryptography.md5
import com.guilhermeb.mymoneycompose.common.helper.DataStorePreferencesHelper
import com.guilhermeb.mymoneycompose.common.helper.SharedPreferencesHelper
import com.guilhermeb.mymoneycompose.common.helper.getPreferencesDataStoreKey
import com.guilhermeb.mymoneycompose.common.helper.getSharedPreferencesKey
import com.guilhermeb.mymoneycompose.model.repository.contract.AsyncProcess
import com.guilhermeb.mymoneycompose.viewmodel.authentication.AuthenticationViewModel
import com.guilhermeb.mymoneycompose.common.validation.isPasswordValid
import com.guilhermeb.mymoneycompose.viewmodel.account.state.ChangePasswordFormState
import com.guilhermeb.mymoneycompose.viewmodel.money.MoneyViewModel
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val moneyViewModel: MoneyViewModel,
    private val authenticationViewModel: AuthenticationViewModel
) : ViewModel() {

    // == -- Account == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- ==

    fun signOut() {
        authenticationViewModel.signOut()
    }

    fun deleteUser(asyncProcess: AsyncProcess<String?>) {
        val userEmail = authenticationViewModel.getCurrentUserEmail()
        authenticationViewModel.deleteUser(object : AsyncProcess<String?> {
            override fun onComplete(isSuccessful: Boolean, result: String?) {
                if (isSuccessful) {
                    userEmail?.let {
                        moneyViewModel.removeAllMoneyItemsByUser(userEmail)
                    }
                    removeUserPreferences(userEmail)
                }
                asyncProcess.onComplete(isSuccessful, result)
            }
        })
    }

    private fun removeUserPreferences(userEmail: String?) {
        DataStorePreferencesHelper.getInstance().removeStringFromDataStorePreferences(
            getPreferencesDataStoreKey(
                userEmail,
                Constants.PASSWORD
            )
        )
        val sharedPreferencesHelper = SharedPreferencesHelper.getInstance()
        sharedPreferencesHelper.remove(getSharedPreferencesKey(userEmail, Constants.LOCALE))
        sharedPreferencesHelper.remove(getSharedPreferencesKey(userEmail, Constants.CURRENCY))
        sharedPreferencesHelper.remove(getSharedPreferencesKey(userEmail, Constants.NIGHT_MODE))
    }

    // == -- Change Password == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- ==

    private val _changePasswordFormState = MutableLiveData<ChangePasswordFormState>()
    val changePasswordFormState: LiveData<ChangePasswordFormState> = _changePasswordFormState

    fun changePasswordFormDataChanged(
        currentPassword: String,
        newPassword: String,
        newPasswordRepeated: String
    ) {
        val isFormFilled =
            currentPassword.isNotEmpty() && newPassword.isNotEmpty() && newPasswordRepeated.isNotEmpty()
        _changePasswordFormState.value = ChangePasswordFormState(isFormCompleted = isFormFilled)
    }

    fun isChangePasswordFormDataValid(
        currentPassword: String,
        newPassword: String,
        newPasswordRepeated: String
    ): Boolean {
        val passwordFromDataStore =
            DataStorePreferencesHelper.getInstance()
                .getStringFromDataStorePreferences(getPreferencesDataStoreKey(Constants.PASSWORD))
        if (!isPasswordValid(currentPassword) || md5(currentPassword) != passwordFromDataStore) {
            _changePasswordFormState.value =
                ChangePasswordFormState(
                    isFormCompleted = true,
                    currentPasswordError = R.string.invalid_password
                )
        } else if (!isPasswordValid(newPassword)) {
            _changePasswordFormState.value =
                ChangePasswordFormState(
                    isFormCompleted = true,
                    newPasswordError = R.string.invalid_password
                )
        } else if (!isPasswordValid(newPasswordRepeated)) {
            _changePasswordFormState.value =
                ChangePasswordFormState(
                    isFormCompleted = true,
                    newPasswordRepeatedError = R.string.invalid_password
                )
        } else if (newPassword != newPasswordRepeated) {
            _changePasswordFormState.value =
                ChangePasswordFormState(
                    isFormCompleted = true,
                    differentPasswordError = R.string.the_passwords_are_different
                )
        } else {
            _changePasswordFormState.value =
                ChangePasswordFormState(isFormCompleted = true, isDataValid = true)
            return true
        }
        return false
    }

    fun updatePassword(newPassword: String, asyncProcess: AsyncProcess<String?>) {
        authenticationViewModel.updatePassword(newPassword, asyncProcess)
    }

    // == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- == -- ==

    fun getCurrentUserEmail(): String? {
        return authenticationViewModel.getCurrentUserEmail()
    }
}