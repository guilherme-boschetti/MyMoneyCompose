package com.guilhermeb.mymoneycompose.di.module

import com.guilhermeb.mymoneycompose.model.data.remote.firebase.authentication.FirebaseAuthentication
import com.guilhermeb.mymoneycompose.model.repository.contract.Authenticable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticableModule {

    @Singleton
    @Binds
    abstract fun bindAuthenticable(authenticableImpl: FirebaseAuthentication): Authenticable
}