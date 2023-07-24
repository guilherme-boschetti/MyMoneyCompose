package com.guilhermeb.mymoneycompose.common.validation

/**
 * Password validation.
 */
fun isPasswordValid(password: String): Boolean {
    return password.isNotEmpty()
}