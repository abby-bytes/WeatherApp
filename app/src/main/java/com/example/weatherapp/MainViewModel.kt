package com.example.weatherapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.dao.Weather
import com.example.weatherapp.data.WeatherApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

class MainViewModel : ViewModel() {

    var weather by mutableStateOf<Weather?>(null)
        private set

    private val api = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    init {
        getWeather("Halifax, Canada")
    }

    fun getWeather(query: String) {
        viewModelScope.launch {
            try {
                weather = api.getWeather(q = query)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}