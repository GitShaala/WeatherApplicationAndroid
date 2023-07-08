package com.app.weatherapplication.data.repository

import android.util.Log
import com.app.weatherapplication.data.Storage
import com.app.weatherapplication.model.WeatherData
import com.app.weatherapplication.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val storage : Storage, private val apiServiceImp: ApiServiceImpl) {

    fun getCityData(city:String):Flow<WeatherData> = flow {
        val response= apiServiceImp.getCity(city,"a45bda185288cef6b03035dd614f61b1")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()

    suspend fun saveCurrentCity(city: String){
        storage.saveCurrentCity(city)
    }

    suspend fun getCurrentCity() : Flow<String> = storage.getCurrentCity()

}