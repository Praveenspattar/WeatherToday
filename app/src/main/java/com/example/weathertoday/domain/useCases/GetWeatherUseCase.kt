package com.example.weathertoday.domain.useCases

import com.example.weathertoday.data.remote.dto.WeatherDto
import com.example.weathertoday.domain.repository.WeatherRepo

class GetWeatherUseCase(
    private val weatherRepo: WeatherRepo
) {
    suspend operator fun invoke(location: String): WeatherDto {
        return weatherRepo.getCurrentWeather(location)
    }
}