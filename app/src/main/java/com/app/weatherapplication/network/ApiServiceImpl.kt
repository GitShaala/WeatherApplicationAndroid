package com.app.weatherapplication.network
import com.app.weatherapplication.model.WeatherData
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun getCity(city:String,appId:String): WeatherData = apiService.getCityData(city,appId,7)
}