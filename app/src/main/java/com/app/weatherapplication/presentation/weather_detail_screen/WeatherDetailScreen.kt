package com.app.weatherapplication.presentation.weather_detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.weatherapplication.model.Forecast
import com.app.weatherapplication.ui.theme.ThemeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(forecast: Forecast){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Weather Detail")
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = ThemeColor))
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it), horizontalAlignment = Alignment.Start) {
            Text(text = "wind : ${forecast.wind?.speed} km/h", modifier = Modifier.wrapContentSize().padding(8.dp))
            Text(text = "visibility :  ${forecast.visibility} m", modifier = Modifier.wrapContentSize().padding(8.dp))
            Text(text = "humidity : ${forecast.main?.humidity} %", modifier = Modifier.wrapContentSize().padding(8.dp))
            Text(text = "pressure : ${forecast.main?.pressure} Pa", modifier = Modifier.wrapContentSize().padding(8.dp))
            Text(text = "clouds :  ${forecast.clouds?.all} %", modifier = Modifier.wrapContentSize().padding(8.dp))
        }
    }
}