package com.app.weatherapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.weatherapplication.model.Forecast
import com.app.weatherapplication.presentation.weather.WeatherScreen
import com.app.weatherapplication.presentation.weather_detail_screen.WeatherDetailScreen
import com.google.gson.Gson

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.WeatherScreen.route
    ) {

        composable(route = Screens.WeatherScreen.route) {
            WeatherScreen(navController)
        }

        composable(route = "${Screens.WeatherDetailScreen.route}/{weatherInfo}") {
            val data = it.arguments?.getString("weatherInfo")
            val forecast = Gson().fromJson(data, Forecast::class.java)
            WeatherDetailScreen(forecast = forecast)
        }
    }
}
