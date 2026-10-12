package com.example.weatherapp.ui.theme.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Class.Weather

@Composable
fun Forecasts() {

    // 7 days of forecast
    val forecastsList = listOf(
        Weather(
            location = "Halifax",
            date = "Sat, October 10",
            temperature = 14.0,
            humidity = 68.0,
            windSpeed = 18.0,
            weatherDescription = "Typically Sunny.",
            resourceId = R.drawable.sunny
        ),
        Weather(
            location = "Halifax",
            date = "Sun, October 11",
            temperature = 15.0,
            humidity = 75.0,
            windSpeed = 23.0,
            weatherDescription = "Partly cloudy.",
            resourceId = R.drawable.partlycloudy
        ),
        Weather(
            location = "Halifax",
            date = "Mon, October 12",
            temperature = 14.0,
            humidity = 79.0,
            windSpeed = 23.0,
            weatherDescription = "Overcast",
            resourceId = R.drawable.cloud
        ),
        Weather(
            location = "Halifax",
            date = "Tue, October 13",
            temperature = 13.0,
            humidity = 75.0,
            windSpeed = 22.0,
            weatherDescription = "Overcast",
            resourceId = R.drawable.cloud
        ),
        Weather(
            location = "Halifax",
            date = "Wed, October 14",
            temperature = 15.0,
            humidity = 81.0,
            windSpeed = 24.0,
            weatherDescription = "Overcast",
            resourceId = R.drawable.cloud
        ),
        Weather(
            location = "Halifax",
            date = "Thu, October 15",
            temperature = 14.0,
            humidity = 73.0,
            windSpeed = 23.0,
            weatherDescription = "Overcast",
            resourceId = R.drawable.cloud
        ),
        Weather(
            location = "Halifax",
            date = "Fri, October 16",
            temperature = 12.0,
            humidity = 84.0,
            windSpeed = 22.0,
            weatherDescription = "Light rain shower",
            resourceId = R.drawable.lightrain
        )
    )

    LazyColumn {
        items(forecastsList) { weather ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp)
            ) {
                // Date at the top of the card
                Text(
                    text = weather.date,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Weather Icon
                Image(
                    painter = painterResource(id = weather.resourceId),
                    contentDescription = weather.weatherDescription,
                    modifier = Modifier.size(70.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Temperature
                Text(
                    text = "${weather.temperature.toInt()}°C High",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // weather description and stats
                Text(
                    text = "${weather.weatherDescription} Humidity ${weather.humidity.toInt()}%.",
                    fontStyle = FontStyle.Italic,
                    fontSize = 13.sp
                )
            }

            // Divider line underneath each day's card matching the UI picture
            Divider(modifier = Modifier.padding(top = 8.dp))
        }
    }
}