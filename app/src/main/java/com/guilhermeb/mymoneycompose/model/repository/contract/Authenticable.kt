package com.guilhermeb.mymoneycompose.model.repository.contract

interface Authenticable {
    fun getCurrentUserUid(): String?
    fun getCurrentUserEmail(): String?
    fun createUser(email: String, password: String, asyncProcess: AsyncProcess<String?>)
    fun signIn(email: String, password: String, asyncProcess: AsyncProcess<String?>)
    fun sendPasswordResetEmail(email: String, asyncProcess: AsyncProcess<String?>)
    fun updatePassword(newPassword: String, asyncProcess: AsyncProcess<String?>)
    fun deleteUser(asyncProcess: AsyncProcess<String?>)
    fun signOut()
}