package com.example.weatherapp.ui.theme.Class

data class Weather(
    val location: String,
    val date: String,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weatherDescription: String,
    val resourceId: Int
)


