package com.example.weathertoday.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import com.example.weathertoday.domain.useCases.GetWeatherUseCase
import com.example.weathertoday.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherData = MutableStateFlow<ApiResult<WeatherDto?>>(ApiResult.Idle)
    val weatherData:StateFlow<ApiResult<WeatherDto?>> = _weatherData.asStateFlow()
    private val _weatherForecaseData = MutableStateFlow<ApiResult<WeatherForecast?>>(ApiResult.Idle)
    val weatherForecastData:StateFlow<ApiResult<WeatherForecast?>> = _weatherForecaseData.asStateFlow()

    fun getWeatherData(location: String = "Belagavi") {
        viewModelScope.launch {
            _weatherData.value = ApiResult.Loading
            _weatherData.value = getWeatherUseCase.getCurrentWeather(location)
        }
    }

    fun getWeatherForecastData(lon:Double, lat:Double) {
        viewModelScope.launch {
            _weatherForecaseData.value = getWeatherUseCase.getForecastWeather(lon,lat)
        }
    }

}