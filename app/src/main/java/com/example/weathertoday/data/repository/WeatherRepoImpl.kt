package com.example.weathertoday.data.repository

import com.example.weathertoday.data.remote.api.WeatherApiService
import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import com.example.weathertoday.domain.repository.WeatherRepo
import com.example.weathertoday.utils.ApiResult
import com.example.weathertoday.utils.Constants

class WeatherRepoImpl(
    private val weatherApiService: WeatherApiService
): WeatherRepo {
    override suspend fun getCurrentWeather(location: String): ApiResult<WeatherDto> {
        return try {
            val result = weatherApiService.getCurrentWeather(location,Constants.apiKey)
            ApiResult.Success(result)
        } catch (e : Exception) {
            ApiResult.Error(e.message ?: "Unknown error", e)
        }
    }

    override suspend fun getForecastWeather(lat: Double, lon: Double): ApiResult<WeatherForecast> {
        return try {
            val result = weatherApiService.getForecastWeather(lat, lon,Constants.apiKey)
            ApiResult.Success(result)
        } catch (e :Exception) {
            ApiResult.Error(e.message ?: "Unknown error", e)
        }
    }
}