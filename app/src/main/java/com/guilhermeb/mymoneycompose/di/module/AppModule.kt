package com.guilhermeb.mymoneycompose.di.module

import android.content.Context
import com.guilhermeb.mymoneycompose.model.data.local.datastore.preferences.DataStorePrefs
import com.guilhermeb.mymoneycompose.model.data.local.datastore.preferences.dataaccess.DataStorePrefsDataAccess
import com.guilhermeb.mymoneycompose.model.data.local.room.dao.money.MoneyDao
import com.guilhermeb.mymoneycompose.model.data.local.room.database.MyMoneyComposeDB
import com.guilhermeb.mymoneycompose.model.data.local.sharedpreferences.SharedPrefs
import com.guilhermeb.mymoneycompose.model.data.local.sharedpreferences.dataaccess.SharedPrefsDataAccess
import com.guilhermeb.mymoneycompose.model.data.remote.firebase.authentication.FirebaseAuthentication
import com.guilhermeb.mymoneycompose.model.data.remote.firebase.rtdb.FirebaseRealTimeDataBase
import com.guilhermeb.mymoneycompose.model.data.remote.retrofit.currency.CurrencyRetrofitClient
import com.guilhermeb.mymoneycompose.model.data.remote.retrofit.currency.api.CurrencyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuthentication {
        return FirebaseAuthentication()
    }

    @Singleton
    @Provides
    fun provideFirebaseRealTimeDataBase(firebaseAuthentication: FirebaseAuthentication): FirebaseRealTimeDataBase {
        return FirebaseRealTimeDataBase(firebaseAuthentication)
    }

    @Singleton
    @Provides
    fun provideMyMoneyDB(@ApplicationContext context: Context): MyMoneyComposeDB {
        return MyMoneyComposeDB.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideMoneyDao(myMoneyComposeDB: MyMoneyComposeDB): MoneyDao {
        return myMoneyComposeDB.moneyDao
    }

    @Singleton
    @Provides
    fun provideSharedPrefs(@ApplicationContext context: Context): SharedPrefs {
        return SharedPrefs.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideSharedPrefsDataAccess(sharedPrefs: SharedPrefs): SharedPrefsDataAccess {
        return SharedPrefsDataAccess(sharedPrefs)
    }

    @Singleton
    @Provides
    fun provideDataStorePrefs(@ApplicationContext context: Context): DataStorePrefs {
        return DataStorePrefs.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDataStorePrefsDataAccess(dataStorePrefs: DataStorePrefs): DataStorePrefsDataAccess {
        return DataStorePrefsDataAccess(dataStorePrefs)
    }

    @Singleton
    @Provides
    fun provideCurrencyRetrofitClient(): Retrofit {
        return CurrencyRetrofitClient.getInstance()
    }

    @Singleton
    @Provides
    fun provideCurrencyApi(retrofitClient: Retrofit): CurrencyApi {
        return retrofitClient.create(CurrencyApi::class.java)
    }
}