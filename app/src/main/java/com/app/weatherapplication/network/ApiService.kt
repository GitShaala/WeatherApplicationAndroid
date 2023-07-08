package com.app.weatherapplication.network

import com.app.weatherapplication.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast/")
    suspend fun getCityData(
        @Query("q") q:String,
        @Query("appid") appId:String,
        @Query("cnt") count: Int
    ): WeatherData
}