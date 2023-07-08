package com.app.weatherapplication.data

import javax.inject.Inject

/** here you can pass any DataBase which Implements LocalCache*/
class Storage @Inject constructor(private val localCache: LocalCache) {
    suspend fun getCurrentCity() = localCache.getCity()
    suspend fun saveCurrentCity(city: String)  = localCache.saveCity(city)
}