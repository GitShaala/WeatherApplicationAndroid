package com.app.weatherapplication.presentation.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.weatherapplication.data.repository.WeatherRepository
import com.app.weatherapplication.model.WeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _weatherResponse: MutableStateFlow<WeatherData?> = MutableStateFlow(null)
    val weatherResponse: StateFlow<WeatherData?> = _weatherResponse

    private val _currentCity: MutableStateFlow<String> = MutableStateFlow("kolkata")
    val currentCity: StateFlow<String> = _currentCity

    private val searchChannel = MutableSharedFlow<String>(1)


     fun getCurrentCity(){
         viewModelScope.launch {
             weatherRepository.getCurrentCity().catch { exception ->
             }.collect { city ->
                 _currentCity.value = city
             }
         }
    }

    fun updateCurrentCity(){
        viewModelScope.launch {
            weatherRepository.saveCurrentCity(currentCity.value)
        }
    }

    fun setSearchQuery(search: String) {
        _currentCity.value = search
        searchChannel.tryEmit(search)
    }

    init {
        getCityData()
    }

    private fun getCityData() {
        viewModelScope.launch {
            searchChannel
                .flatMapLatest { search ->
                    weatherRepository.getCityData(search)
                }.catch { e ->
                    Log.d("WeatherViewModel Error", "${e.message}")
                }.collect { response ->
                     updateCurrentCity()
                    _weatherResponse.value = response
                }
        }
    }
}