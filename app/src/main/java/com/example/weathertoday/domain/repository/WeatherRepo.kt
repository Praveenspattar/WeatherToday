package com.example.weathertoday.domain.repository

import com.example.weathertoday.data.remote.dto.WeatherDto

interface WeatherRepo {
    suspend fun getCurrentWeather(location: String): WeatherDto
}