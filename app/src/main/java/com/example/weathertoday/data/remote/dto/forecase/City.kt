package com.example.weathertoday.data.remote.dto.forecase

import com.example.weathertoday.data.remote.dto.Coord

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)