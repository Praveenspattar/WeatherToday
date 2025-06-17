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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathertoday.R
import com.example.weathertoday.data.remote.dto.forecase.WeatherForecast
import com.example.weathertoday.data.remote.dto.Clouds
import com.example.weathertoday.data.remote.dto.Coord
import com.example.weathertoday.data.remote.dto.weather.Main
import com.example.weathertoday.data.remote.dto.weather.Sys
import com.example.weathertoday.data.remote.dto.Weather
import com.example.weathertoday.data.remote.dto.weather.WeatherDto
import com.example.weathertoday.data.remote.dto.weather.Wind
import com.example.weathertoday.presentation.MainActivity
import com.example.weathertoday.presentation.ui.theme.Primary
import com.example.weathertoday.presentation.ui.theme.Secondary
import com.example.weathertoday.presentation.ui.theme.WeatherTodayTheme
import com.example.weathertoday.presentation.ui.theme.textColor
import com.example.weathertoday.presentation.viewModel.WeatherViewModel
import com.example.weathertoday.utils.ApiResult
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun weatherMainScreen(modifier: Modifier, viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherData.collectAsState()
    val forecasetState by viewModel.weatherForecastData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getWeatherData()
    }

    when(weatherState) {
        ApiResult.Idle -> {
            viewModel.getWeatherData()
        }
        ApiResult.Loading -> {
            //Show loader
        }
        is ApiResult.Success -> {
            val weather = (weatherState as ApiResult.Success).data
            //TemperatureUI()
        }
        is ApiResult.Error -> {
            TODO()
        }
    }
}

@Composable
fun TemperatureUI(current_weather: WeatherDto?,weatherForecast: WeatherForecast?, modifier: Modifier) {

    // Handle null case at the entry point
    if (current_weather == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading weather data...")
        }
        return
    }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(13.dp)
    ) {
        item {
            Row(
                modifier = modifier
                    .padding(horizontal = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.locationimg), null,
                    modifier = Modifier
                        .size(35.dp)
                )

                Text(
                    text = current_weather?.name.toString(),
                    color = textColor,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Column {
                    Text(
                        text = "${current_weather?.main?.temp?.minus(273.15)
                            ?.toInt().toString()}°",
                        color = textColor,
                        fontSize = 60.sp
                    )
                    Text(
                        text = current_weather?.weather?.get(0)?.description.toString(),
                        color = textColor,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Feels like ${current_weather?.main?.feels_like?.minus(273.15)
                            ?.toInt().toString()}°",
                        color = textColor,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 25.dp),
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                WeatherIcon(current_weather?.weather?.get(0)?.icon?:"0")
            }
        }

        item {
            WeatherDetails(current_weather)
            Spacer(Modifier.size(17.dp))
        }

        item {
            ForecastDisplay(weatherForecast)
            Spacer(Modifier.size(17.dp))
        }

        item {
            SunRiseView(current_weather?.sys?.sunrise,current_weather?.sys?.sunset)
        }
    }

}

@Composable
fun WeatherDetails(currentWeather: WeatherDto?) {
    Column {
        WeatherRow(
            items = listOf(
                Triple("Wind", R.drawable.wind_icon, "${(currentWeather?.wind?.speed?.times(3.6)?.toInt()) ?: "--"} km/h"),
                Triple("Humidity", R.drawable.humidity_icon, "${(currentWeather?.main?.humidity) ?: "--"} %")
            )
        )
        Spacer(Modifier.height(25.dp))
        WeatherRow(
            items = listOf(
                Triple("Pressure", R.drawable.pressure_icon, "${(currentWeather?.main?.pressure) ?: "--"} hpa"),
                Triple("Visibility", R.drawable.eye_icon, "${(currentWeather?.visibility?.div(1000)) ?: "--"} km")
            )
        )
    }
}

@Composable
fun WeatherRow(items: List<Triple<String, Int, String>>) {
    Row(Modifier.fillMaxWidth()) {
        items.forEachIndexed { index, (label, icon, value) ->
            WeatherCard(label, icon, value, Modifier.weight(4.7f))
            if (index == 0) Spacer(Modifier.weight(0.6f))
        }
    }
}

@Composable
fun WeatherCard(label: String, iconRes: Int, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Secondary)
            .padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Row {
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                label,
                color = textColor,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
        Text(
            text = value,
            color = textColor,
            fontSize = 35.sp,
            lineHeight = 40.sp,
            modifier = Modifier.padding(top = 15.dp)
        )
    }
}

@Composable
fun SunRiseView(sunrise: Int?, sunset: Int?) {
    val sunriseTime = sunrise?.let { getTime(it.toLong(),"h:mm a") }
    val sunsetTime = sunset?.let { getTime(it.toLong(),"h:mm a") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Secondary)
            .padding(horizontal = 15.dp, vertical = 35.dp)
    ) {
        Image(painter = painterResource(R.drawable.sun_rise),
            contentDescription = null,
            Modifier
                .size(100.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 18.dp)
        ) {
            Text("Sunrise", fontWeight = FontWeight.Light,
                color = textColor, fontSize = 15.sp)
            Text(sunriseTime.toString(), fontWeight = FontWeight.Medium,
                color = textColor, fontSize = 25.sp)
            Spacer(Modifier.size(8.dp))
            Text("Sunset", fontWeight = FontWeight.Light,
                color = textColor, fontSize = 15.sp)
            Text(sunsetTime.toString(), fontWeight = FontWeight.Medium,
                color = textColor, fontSize = 25.sp)
        }
    }
}

@Composable
fun ForecastDisplay(weatherForecast: WeatherForecast?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Secondary)
            .padding(20.dp)
    ) {
        Text(
            text = "Forecast ",
            color = textColor,
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 7.dp)
        )

        weatherForecast?.list?.forEachIndexed { index, item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp, horizontal = 3.dp)
            ) {
                Text(
                    text =
                        getTime(
                            item.dt.toLong(),
                            "EEEE"
                        ),
                    fontSize = 18.sp,
                    color = textColor,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1.7f)
                )
                Text(
                    text =
                        item.weather[0].main,
                    color = textColor,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text =
                        "${item.main.temp.minus(273.15).toInt()}°C",
                    color = textColor,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

}

@Composable
fun mForecastDisplay(weatherForecast: WeatherForecast?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Secondary)
            .padding(15.dp)
    ) {
        Text(
            text = "Forecast ",
            color = textColor,
            fontSize = 19.sp,
            fontWeight = FontWeight.Normal
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 7.dp)
        )

        // No need for LazyColumn here since we're only showing 7 items
        Column(
            Modifier.fillMaxWidth()
        ) {
            weatherForecast?.list?.take(7)?.forEach { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 3.dp)
                ) {
                    Text(
                        text = getTime(item.dt.toLong(), "EEE, MMM dd"),
                        color = textColor,
                        modifier = Modifier.weight(1.7f)
                    )
                    Text(
                        text = item.weather[0].main,
                        color = textColor,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${item.main.temp.minus(273.15).toInt()}°C",
                        color = textColor,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
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

fun getTime(unixSecond: Long, format: String): String {
    val instant = Instant.ofEpochSecond(unixSecond)
    val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}

/*
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
                    weather = listOf(Weather(description = "Clear sky", icon = "01d", id = 10, main = "Clouds")),
                    wind = Wind(speed = 5.5, deg = 240)
                )
                TemperatureUI(*/
/*responseData*//*
dummyWeatherDto, MainActivity.dummyWeatherForecast, Modifier.padding(innerPadding))
            }
        }
    }
}*/
