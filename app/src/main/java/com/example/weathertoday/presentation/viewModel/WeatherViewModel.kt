package com.example.weathertoday.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertoday.data.remote.dto.WeatherDto
import com.example.weathertoday.domain.useCases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherDto?>(null)

    val weatherData:StateFlow<WeatherDto?> = _weatherData.asStateFlow()

    fun getWeatherData(location: String = "Belagavi") {
        viewModelScope.launch {
            _weatherData.value = getWeatherUseCase.invoke(location)
        }
    }

}