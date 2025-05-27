package com.example.weathertoday.presentation

import android.os.Bundle
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weathertoday.presentation.screen.TemperatureUI
import com.example.weathertoday.presentation.ui.theme.SkyBlue
import com.example.weathertoday.presentation.ui.theme.WeatherTodayTheme
import com.example.weathertoday.presentation.viewModel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.weathertoday.data.remote.dto.Clouds
import com.example.weathertoday.data.remote.dto.Coord
import com.example.weathertoday.data.remote.dto.Main
import com.example.weathertoday.data.remote.dto.Sys
import com.example.weathertoday.data.remote.dto.Weather
import com.example.weathertoday.data.remote.dto.WeatherDto
import com.example.weathertoday.data.remote.dto.Wind

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //weatherViewModel.getWeatherData()

        setContent {
            WeatherTodayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val lifecycleOwner = LocalLifecycleOwner.current
                    val responseData by weatherViewModel.weatherData.collectAsStateWithLifecycle(lifecycleOwner.lifecycle)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(SkyBlue)
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
                        TemperatureUI(/*responseData*/dummyWeatherDto,Modifier.padding(innerPadding))
                    }
                }
            }
        }
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
                    .background(SkyBlue)
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
                TemperatureUI(/*responseData*/dummyWeatherDto,Modifier.padding(innerPadding))
            }
        }
    }
}