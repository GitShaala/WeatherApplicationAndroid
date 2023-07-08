package com.app.weatherapplication.data

import kotlinx.coroutines.flow.Flow
/** Adding rules for each database (can be sqlite , room or datastore)*/
interface LocalCache {
    suspend fun getCity() : Flow<String>
    suspend fun saveCity(city : String)
}