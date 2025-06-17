package com.example.weathertoday.data.remote.api

import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): WeatherDto

    @GET("data/2.5/forecast")
    suspend fun getForecastWeather(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("appid") apiKey: String
    ): WeatherForecast

}