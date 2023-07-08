package com.app.weatherapplication.navigation

sealed class Screens(val route: String) {
    object WeatherScreen : Screens(route = "weather_screen")
    object WeatherDetailScreen : Screens(route = "weather_detail_screen")
}