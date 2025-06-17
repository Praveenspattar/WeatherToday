package com.example.weathertoday.domain.repository

import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import com.example.weathertoday.utils.ApiResult

interface WeatherRepo {
    suspend fun getCurrentWeather(location: String): ApiResult<WeatherDto>
    suspend fun getForecastWeather(lat:Double, lon:Double): ApiResult<WeatherForecast>
}