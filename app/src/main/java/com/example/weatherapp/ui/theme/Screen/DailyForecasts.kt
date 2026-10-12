package com.example.weatherapp.ui.theme.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Class.Weather

@Composable
fun DailyForecast() {

    val dailyForecasts = listOf(
        Weather(
            location = "Halifax",
            date = "October 1, 2026",
            temperature = 18.0,
            humidity = 84.0,
            windSpeed = 15.0,
            weatherDescription = "Sunny",
            resourceId = R.drawable.cwsunny
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD0E1FD),
                        Color(0xFFF0F5FF),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        items(dailyForecasts) { weather ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp)
            ) {
                Image(
                    painter = painterResource(id = weather.resourceId),
                    contentDescription = weather.weatherDescription,
                    modifier = Modifier.size(150.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(
                        text = weather.location,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = weather.date)
                    Text(text = "${weather.temperature}°C - ${weather.weatherDescription}")
                    Text(
                        text = "Wind: ${weather.windSpeed} km/h | Humidity: ${weather.humidity}%",
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}