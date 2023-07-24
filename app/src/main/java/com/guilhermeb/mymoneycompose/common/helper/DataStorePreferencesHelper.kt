package com.guilhermeb.mymoneycompose.common.helper

import com.guilhermeb.mymoneycompose.MyMoneyComposeApplication
import com.guilhermeb.mymoneycompose.model.repository.datastore.preferences.DataStorePreferencesRepository
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class DataStorePreferencesHelper {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface DataStorePreferencesRepositoryInterface {
        fun getDataStorePreferencesRepository(): DataStorePreferencesRepository
    }

    private val dataStorePreferencesRepository: DataStorePreferencesRepository

    init {
        val dataStorePrefsInterface = EntryPoints.get(
            MyMoneyComposeApplication.getInstance().applicationContext,
            DataStorePreferencesRepositoryInterface::class.java
        )
        dataStorePreferencesRepository = dataStorePrefsInterface.getDataStorePreferencesRepository()
    }

    /**
     * Singleton instance
     */
    companion object {
        @Volatile
        private var instance: DataStorePreferencesHelper? = null

        @Synchronized
        fun getInstance(): DataStorePreferencesHelper {
            if (instance == null) {
                instance =
                    DataStorePreferencesHelper()
            }
            return instance!!
        }
    }

    fun saveStringIntoDataStorePreferences(preferencesKey: String, value: String) {
        dataStorePreferencesRepository.saveStringIntoDataStorePreferences(preferencesKey, value)
    }

    fun getStringFromDataStorePreferences(preferencesKey: String): String? {
        return dataStorePreferencesRepository.getStringFromDataStorePreferences(preferencesKey)
    }

    fun removeStringFromDataStorePreferences(preferencesKey: String) {
        dataStorePreferencesRepository.removeStringFromDataStorePreferences(preferencesKey)
    }
}

fun getPreferencesDataStoreKey(key: String): String {
    return MyMoneyComposeApplication.getInstance().getCurrentUserEmail() + "/" + key
}

fun getPreferencesDataStoreKey(userEmail: String?, key: String): String {
    return "$userEmail/$key"
}