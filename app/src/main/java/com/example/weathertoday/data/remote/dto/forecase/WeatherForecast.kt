package com.example.weathertoday.data.remote.dto.forecase

data class WeatherForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Item0>,
    val message: Int
)