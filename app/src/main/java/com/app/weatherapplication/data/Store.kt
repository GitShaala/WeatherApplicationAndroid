package com.app.weatherapplication.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/** Implementing LocalCache
 * This Store will use android's PreferenceStore to persist Data **/
class Store(private val dataStore: DataStore<Preferences>) : LocalCache {
    companion object {
        val CURRENT_CITY  = stringPreferencesKey("current_city")
    }

    override suspend fun getCity(): Flow<String> = dataStore.data.map { preferences ->
        preferences[CURRENT_CITY] ?: "kolkata"
    }

    override suspend fun saveCity(city : String) {
        dataStore.edit { preferences ->
            preferences[CURRENT_CITY] = city
        }
    }
}