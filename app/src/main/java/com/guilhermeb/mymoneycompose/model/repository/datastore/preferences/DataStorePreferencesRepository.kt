package com.guilhermeb.mymoneycompose.model.repository.datastore.preferences

import com.guilhermeb.mymoneycompose.model.data.local.datastore.preferences.dataaccess.DataStorePrefsDataAccess

class DataStorePreferencesRepository(private val dataStorePrefsDataAccess: DataStorePrefsDataAccess) {

    fun saveStringIntoDataStorePreferences(preferencesKey: String, value: String) {
        dataStorePrefsDataAccess.saveStringIntoDataStorePreferences(preferencesKey, value)
    }

    fun getStringFromDataStorePreferences(preferencesKey: String): String? {
        return dataStorePrefsDataAccess.getStringFromDataStorePreferences(preferencesKey)
    }

    fun removeStringFromDataStorePreferences(preferencesKey: String) {
        dataStorePrefsDataAccess.removeStringFromDataStorePreferences(preferencesKey)
    }
}