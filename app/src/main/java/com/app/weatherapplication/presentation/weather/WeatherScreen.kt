package com.app.weatherapplication.presentation.weather

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.weatherapplication.R
import com.app.weatherapplication.model.Forecast
import com.app.weatherapplication.model.Weather
import com.app.weatherapplication.navigation.Screens
import com.app.weatherapplication.ui.theme.ThemeColor
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun WeatherScreen(
    navigationController: NavHostController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val state = weatherViewModel.weatherResponse.collectAsState()
    val city = weatherViewModel.currentCity.collectAsState()
    val context = LocalContext.current
    val shouldShowSearch = remember {
        mutableStateOf(false)
    }
    weatherViewModel.getCurrentCity()
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(city.value)
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = ThemeColor))
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f)
                        .background(ThemeColor)
                )
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .height(30.dp)
                        .align(Alignment.TopEnd), shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .height(30.dp)
                            .padding(start = 8.dp, end = 8.dp)
                            .clickable {
                                shouldShowSearch.value = true
                            },
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "edit location",
                            modifier = Modifier
                                .width(10.dp)
                                .aspectRatio(1f)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(text = city.value, color = Color.Black, fontSize = 12.sp)
                    }

                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .width((city.value.length + 30).dp)
                            .background(Color.Black)
                    )
                }

                if (shouldShowSearch.value) {
                    SearchBar(
                        onSearch = { searchedLocation ->
                            shouldShowSearch.value = false
                            if (searchedLocation.isNotEmpty()) {
                                weatherViewModel.setSearchQuery(searchedLocation)
                            } else {
                                Toast.makeText(
                                    context,
                                    "searched location is empty",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }, modifier = Modifier
                            .fillMaxWidth(0.65f)
                            .height(50.dp)
                    )
                }
            }
            LaunchedEffect(key1 = city.value) {
                weatherViewModel.setSearchQuery(city.value)
            }

            state.value?.let { weatherData ->
                weatherViewModel.updateCurrentCity()
                ShowWeather(list = weatherData.list, navigationController)
            }
        }
    }
}

@Composable
fun ShowWeather(list: List<Forecast>, controller: NavHostController) {
    Spacer(modifier = Modifier.height(20.dp))
    LazyRow(
        modifier = Modifier.height(130.dp),
        contentPadding = PaddingValues(10.dp),
    ) {
        items(list) { forecast ->
            WeatherCard(forecast = forecast, navigationController = controller)
        }
    }
}

@Composable
fun WeatherCard(forecast: Forecast, navigationController: NavHostController) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .wrapContentHeight()
            .padding(12.dp)
            .clickable {
                navigateToWeatherDetailScreen(forecast, navigationController)
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (((forecast.main?.temp?.minus(273f))?.times(100.0))?.roundToInt()
                    ?.div(100.0)).toString() + "°",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            val resId = forecast.weather?.get(0)?.let { getWeatherIcon(it) }
            Image(
                painterResource(resId ?: R.drawable.haze),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }
    }
}

fun getWeatherIcon(weather: Weather): Int {
    return when (weather.description) {
        "clear sky", "mist" -> {
            R.drawable.clouds
        }

        "haze", "overcast clouds" -> {
            R.drawable.haze
        }

        else -> {
            R.drawable.rain
        }
    }
}

fun navigateToWeatherDetailScreen(forecast: Forecast, navigationController: NavHostController) {
    val jsonString = Gson().toJson(forecast)
    navigationController.navigate("${Screens.WeatherDetailScreen.route}/$jsonString")
}