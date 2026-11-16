package com.example.weatherapp.ui.theme.models

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Location(
    val name: String,
    val region: String,
    val country: String
)

data class Current(
    val temp_c: Double,
    val temp_f: Double,
    val condition: Condition,
    val wind_kph: Double,
    val humidity: Int
) {
    val tempCelsius: Double get() = temp_c
    val windKph: Double get() = wind_kph
}

data class Condition(
    val text: String,
    val icon: String
)

data class Forecast(
    val forecastday: List<ForecastDay>
) {
    val forecastDay: List<ForecastDay> get() = forecastday
}

data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val maxwind_kph: Double,
    val avghumidity: Double,
    val condition: Condition
) {
    val maxTempC: Double get() = maxtemp_c
    val minTempC: Double get() = mintemp_c
    val maxWindKph: Double get() = maxwind_kph
    val avgHumidity: Double get() = avghumidity
}