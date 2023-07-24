package com.guilhermeb.mymoneycompose.model.repository.contract

interface AsyncProcess<T> {
    fun onComplete(isSuccessful: Boolean, result: T?)
}