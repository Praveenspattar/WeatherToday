package com.example.weathertoday.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathertoday.R
import com.example.weathertoday.data.remote.dto.Clouds
import com.example.weathertoday.data.remote.dto.Coord
import com.example.weathertoday.data.remote.dto.Main
import com.example.weathertoday.data.remote.dto.Sys
import com.example.weathertoday.data.remote.dto.Weather
import com.example.weathertoday.data.remote.dto.WeatherDto
import com.example.weathertoday.data.remote.dto.Wind
import com.example.weathertoday.presentation.ui.theme.SkyBlue
import com.example.weathertoday.presentation.ui.theme.WeatherTodayTheme


@Composable
fun TemperatureUI(weatherDto: WeatherDto?, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(start = 20.dp, top = 20.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp)
            ) {
                Text(
                    weatherDto?.name?:"",
                    fontSize = 45.sp,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(R.drawable.locationimg),
                    contentDescription = "",
                    Modifier
                        .size(33.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = weatherDto?.main?.temp.toString(),
                    fontSize = 65.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                WeatherIcon(weatherDto?.weather?.get(0)?.icon ?: "01d")
            }
            Text(weatherDto?.weather?.get(0)?.main ?:"")
            Text(weatherDto?.main?.temp_min.toString())
            Text(weatherDto?.main?.temp_max.toString())
        }

    }
}

@Composable
fun WeatherIcon(iconCode: String) {
    val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@4x.png"

    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather Icon",
            placeholder = painterResource(R.drawable.placeholder_cld),
            error = painterResource(R.drawable.placeholder_cld),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(170.dp)
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
                    weather = listOf(Weather(description = "Clear sky", icon = "01d", id = 10, main = "Clouds")),
                    wind = Wind(speed = 5.5, deg = 240)
                )
                TemperatureUI(/*responseData*/dummyWeatherDto,Modifier.padding(innerPadding))
            }
        }
    }
}