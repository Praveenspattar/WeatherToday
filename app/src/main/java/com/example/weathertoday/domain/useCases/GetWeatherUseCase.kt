package com.example.weathertoday.domain.useCases

import android.util.Log
import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import com.example.weathertoday.domain.repository.WeatherRepo
import com.example.weathertoday.utils.ApiResult

class GetWeatherUseCase(
    private val weatherRepo: WeatherRepo
) {
    suspend fun getCurrentWeather(location: String): ApiResult<WeatherDto> {
        return weatherRepo.getCurrentWeather(location)
    }

    suspend fun getForecastWeather(lat:Double, lon:Double): ApiResult<WeatherForecast> {
        return weatherRepo.getForecastWeather(lat, lon)
    }
}