package com.guilhermeb.mymoneycompose.di.module

import com.guilhermeb.mymoneycompose.model.data.local.datastore.preferences.dataaccess.DataStorePrefsDataAccess
import com.guilhermeb.mymoneycompose.model.data.local.room.dao.money.MoneyDao
import com.guilhermeb.mymoneycompose.model.data.local.sharedpreferences.dataaccess.SharedPrefsDataAccess
import com.guilhermeb.mymoneycompose.model.data.remote.firebase.rtdb.FirebaseRealTimeDataBase
import com.guilhermeb.mymoneycompose.model.data.remote.retrofit.currency.api.CurrencyApi
import com.guilhermeb.mymoneycompose.model.repository.authentication.AuthenticationRepository
import com.guilhermeb.mymoneycompose.model.repository.contract.Authenticable
import com.guilhermeb.mymoneycompose.model.repository.currency.CurrencyRepository
import com.guilhermeb.mymoneycompose.model.repository.datastore.preferences.DataStorePreferencesRepository
import com.guilhermeb.mymoneycompose.model.repository.money.MoneyRepository
import com.guilhermeb.mymoneycompose.model.repository.sharedpreferences.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: Authenticable): AuthenticationRepository {
        return AuthenticationRepository(auth)
    }

    @Singleton
    @Provides
    fun provideMoneyRepository(
        dataSource: MoneyDao,
        dataBackup: FirebaseRealTimeDataBase
    ): MoneyRepository {
        return MoneyRepository(dataSource, dataBackup)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesRepository(dataSource: SharedPrefsDataAccess): SharedPreferencesRepository {
        return SharedPreferencesRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideDataStorePreferencesRepository(dataSource: DataStorePrefsDataAccess): DataStorePreferencesRepository {
        return DataStorePreferencesRepository(dataSource)
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(currencyApi: CurrencyApi): CurrencyRepository {
        return CurrencyRepository(currencyApi)
    }
}