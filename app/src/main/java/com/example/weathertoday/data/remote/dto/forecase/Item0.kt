package com.example.weathertoday.data.remote.dto.forecase

import com.example.weathertoday.data.remote.dto.Clouds
import com.example.weathertoday.data.remote.dto.Weather

data class Item0(
    val clouds: Clouds,
    val dt: Int,
    val dt_txt: String,
    val main: MainW,
    val pop: Double,
    val rain: Rain,
    val sys: SysW,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: WindW
)