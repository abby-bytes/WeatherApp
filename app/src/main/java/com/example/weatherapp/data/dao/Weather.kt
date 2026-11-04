package com.example.weatherapp.data.dao

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast? = null
)

data class Location(
    val name: String,
    val region: String
)

data class Current(
    @SerializedName("temp_c") val tempCelsius: Float,
    @SerializedName("wind_kph") val windKph: Float,
    val humidity: Int,
    val condition: Condition
)

data class Forecast(
    @SerializedName("forecastday") val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    @SerializedName("maxtemp_c") val maxTempC: Float,
    @SerializedName("mintemp_c") val minTempC: Float,
    @SerializedName("maxwind_kph") val maxWindKph: Float,
    @SerializedName("avghumidity") val avgHumidity: Float,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)