package com.example.weathertoday.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weathertoday.presentation.screen.TemperatureUI
import com.example.weathertoday.presentation.ui.theme.Primary
import com.example.weathertoday.presentation.ui.theme.WeatherTodayTheme
import com.example.weathertoday.presentation.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.weathertoday.data.remote.dto.Clouds
import com.example.weathertoday.data.remote.dto.Coord
import com.example.weathertoday.data.remote.dto.weather.Main
import com.example.weathertoday.data.remote.dto.weather.Sys
import com.example.weathertoday.data.remote.dto.Weather
import com.example.weathertoday.data.remote.dto.forecase.City
import com.example.weathertoday.data.remote.dto.forecase.Item0
import com.example.weathertoday.data.remote.dto.forecase.MainW
import com.example.weathertoday.data.remote.dto.forecase.Rain
import com.example.weathertoday.data.remote.dto.forecase.SysW
import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.forecase.WindW
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import com.example.weathertoday.data.remote.dto.weather.Wind

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherTodayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Primary)
                    ) {
                        val dummyWeatherDto = WeatherDto(
                            base = "stations",
                            clouds = Clouds(all = 75),
                            cod = 200,
                            coord = Coord(lon = 74.5, lat = 15.85),
                            dt = 1686746392,
                            id = 1276200,
                            main = Main(
                                temp = 28.5,
                                feels_like = 28.5,
                                grnd_level = 10,
                                humidity = 10,
                                pressure = 10,
                                sea_level = 10,
                                temp_max = 28.5,
                                temp_min = 28.5
                            ),
                            name = "Belagavi",
                            sys = Sys(
                                country = "IN",
                                sunrise = 1686700000,
                                sunset = 1686745000,
                                id = 10,
                                type = 10
                            ),
                            timezone = 19800,
                            visibility = 10000,
                            weather = listOf(Weather(description = "Clear sky", icon = "", id = 10, main = "clouds")),
                            wind = Wind(speed = 5.5, deg = 240)
                        )
                        /*TemperatureUI(
                            currentWeather*//*dummyWeatherDto*//*,
                            forecastWeather*//*dummyWeatherForecast*//*,
                            Modifier.padding(innerPadding)
                        )*/
                    }
                }
            }
        }
    }

    companion object{
        val dummyWeatherForecast = WeatherForecast(
            city = City(
                coord = Coord(lat = 44.34, lon = 10.99),
                country = "IT",
                id = 123456,
                name = "Test City",
                population = 100000,
                sunrise = 1627701212,
                sunset = 1627753895,
                timezone = 7200
            ),
            cnt = 24,
            cod = "200",
            message = 0,
            list = List(24) { index ->
                val baseTime = 1627747200
                val dt = baseTime + (index * 3 * 60 * 60)
                val hour = (index * 3) % 24
                val date = 14 + (index / 8)
                Item0(
                    dt = dt,
                    dt_txt = "2025-06-${"%02d".format(date)} ${"%02d:00:00".format(hour)}",
                    main = MainW(
                        temp = 300.0 + index,
                        feels_like = 299.0 + index,
                        temp_min = 299.0 + index,
                        temp_max = 301.0 + index,
                        pressure = 1010 + (index % 5),
                        sea_level = 1010 + (index % 5),
                        grnd_level = 1000 + (index % 3),
                        humidity = 60 + (index % 10),
                        temp_kf = 0.0
                    ),
                    weather = listOf(
                        Weather(
                            id = 800 + (index % 3),
                            main = listOf("Clear", "Clouds", "Rain")[index % 3],
                            description = listOf("clear sky", "scattered clouds", "light rain")[index % 3],
                            icon = listOf("01d", "02d", "10d")[index % 3]
                        )
                    ),
                    clouds = Clouds(all = (index * 3) % 100),
                    wind = WindW(speed = 3.0 + (index % 5), deg = 90 + (index * 10) % 360, gust = 1.5 + (index % 3)),
                    visibility = 10000,
                    pop = 0.1 * (index % 5),
                    rain = Rain(3.0),
                    sys = SysW(pod = if (hour in 6..18) "d" else "n")
                )
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherTodayTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
            ) {
                val dummyWeatherDto = WeatherDto(
                    base = "stations",
                    clouds = Clouds(all = 75),
                    cod = 200,
                    coord = Coord(lon = 74.5, lat = 15.85),
                    dt = 1686746392,
                    id = 1276200,
                    main = Main(
                        temp = 28.5,
                        feels_like = 28.5,
                        grnd_level = 10,
                        humidity = 10,
                        pressure = 10,
                        sea_level = 10,
                        temp_max = 28.5,
                        temp_min = 28.5
                    ),
                    name = "Belagavi",
                    sys = Sys(
                        country = "IN",
                        sunrise = 1686700000,
                        sunset = 1686745000,
                        id = 10,
                        type = 10
                    ),
                    timezone = 19800,
                    visibility = 10000,
                    weather = listOf(Weather(description = "Clear sky", icon = "", id = 10, main = "Clouds")),
                    wind = Wind(speed = 5.5, deg = 240)
                )
                //TemperatureUI(/*responseData*/dummyWeatherDto, null, Modifier.padding(innerPadding))
            }
        }
    }
}