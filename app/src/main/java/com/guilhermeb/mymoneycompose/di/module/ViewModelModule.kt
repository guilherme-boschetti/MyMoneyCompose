package com.guilhermeb.mymoneycompose.di.module

import com.guilhermeb.mymoneycompose.model.repository.authentication.AuthenticationRepository
import com.guilhermeb.mymoneycompose.model.repository.currency.CurrencyRepository
import com.guilhermeb.mymoneycompose.model.repository.money.MoneyRepository
import com.guilhermeb.mymoneycompose.viewmodel.account.AccountViewModel
import com.guilhermeb.mymoneycompose.viewmodel.authentication.AuthenticationViewModel
import com.guilhermeb.mymoneycompose.viewmodel.currency.CurrencyConverterViewModel
import com.guilhermeb.mymoneycompose.viewmodel.login.CreateAccountViewModel
import com.guilhermeb.mymoneycompose.viewmodel.login.ForgotPasswordViewModel
import com.guilhermeb.mymoneycompose.viewmodel.login.LoginViewModel
import com.guilhermeb.mymoneycompose.viewmodel.money.MoneyViewModel
import com.guilhermeb.mymoneycompose.viewmodel.money.chart.ChartViewModel
import com.guilhermeb.mymoneycompose.viewmodel.money.file.generator.GenerateFileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ViewModelModule {

    @Singleton
    @Provides
    fun provideAuthenticationViewModel(authenticationRepository: AuthenticationRepository): AuthenticationViewModel {
        return AuthenticationViewModel(authenticationRepository)
    }

    @Singleton
    @Provides
    fun provideLoginViewModel(authenticationViewModel: AuthenticationViewModel): LoginViewModel {
        return LoginViewModel(authenticationViewModel)
    }

    @Singleton
    @Provides
    fun provideCreateAccountViewModel(authenticationViewModel: AuthenticationViewModel): CreateAccountViewModel {
        return CreateAccountViewModel(authenticationViewModel)
    }

    @Singleton
    @Provides
    fun provideForgotPasswordViewModel(authenticationViewModel: AuthenticationViewModel): ForgotPasswordViewModel {
        return ForgotPasswordViewModel(authenticationViewModel)
    }

    @Singleton
    @Provides
    fun provideAccountViewModel(
        moneyViewModel: MoneyViewModel,
        authenticationViewModel: AuthenticationViewModel
    ): AccountViewModel {
        return AccountViewModel(moneyViewModel, authenticationViewModel)
    }

    @Singleton
    @Provides
    fun provideMoneyViewModel(
        moneyRepository: MoneyRepository,
        authenticationViewModel: AuthenticationViewModel
    ): MoneyViewModel {
        return MoneyViewModel(moneyRepository, authenticationViewModel)
    }

    @Singleton
    @Provides
    fun provideChartViewModel(moneyViewModel: MoneyViewModel): ChartViewModel {
        return ChartViewModel(moneyViewModel)
    }

    @Singleton
    @Provides
    fun provideGenerateFileViewModel(moneyViewModel: MoneyViewModel): GenerateFileViewModel {
        return GenerateFileViewModel(moneyViewModel)
    }

    @Singleton
    @Provides
    fun provideCurrencyViewModel(currencyRepository: CurrencyRepository): CurrencyConverterViewModel {
        return CurrencyConverterViewModel(currencyRepository)
    }
}