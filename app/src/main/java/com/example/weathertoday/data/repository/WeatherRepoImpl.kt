package com.example.weathertoday.data.repository

import com.example.weathertoday.data.remote.api.WeatherApiService
import com.example.weathertoday.data.remote.dto.WeatherDto
import com.example.weathertoday.domain.repository.WeatherRepo
import com.example.weathertoday.utils.Constants

class WeatherRepoImpl(
    private val weatherApiService: WeatherApiService
): WeatherRepo {
    override suspend fun getCurrentWeather(location: String): WeatherDto {
        return weatherApiService.getCurrentWeather(location,Constants.apiKey)
    }
}