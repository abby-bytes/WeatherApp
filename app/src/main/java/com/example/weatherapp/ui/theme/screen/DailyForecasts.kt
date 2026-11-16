package com.example.weatherapp.ui.theme.screen

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
import com.example.weatherapp.MainViewModel

@Composable
fun DailyForecast(viewModel: MainViewModel) {
    val weather = viewModel.weather

    weather?.let { data ->
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
            item {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 200.dp)
                ) {
                    // Select local drawable based on condition text
                    val iconRes = getWeatherDrawable(data.current.condition.text)

                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = data.current.condition.text,
                        modifier = Modifier.size(150.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 20.dp)
                    ) {
                        Text(
                            text = "${data.location.name}, ${data.location.region}",
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "${data.current.tempCelsius}°C - ${data.current.condition.text}")
                        Text(
                            text = "Wind: ${data.current.windKph} km/h | Humidity: ${data.current.humidity}%",
                            fontStyle = FontStyle.Italic,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

// Helper function to map condition text to your local PNG drawables
fun getWeatherDrawable(condition: String): Int {
    val lower = condition.lowercase()
    return when {
        lower.contains("sun") || lower.contains("clear") -> R.drawable.sunny
        lower.contains("cloud") || lower.contains("overcast") -> R.drawable.cloud
        lower.contains("rain") || lower.contains("drizzle") -> R.drawable.lightrain
        lower.contains("thunder") || lower.contains("storm") -> R.drawable.thunderstorm
        else -> R.drawable.partlycloudy
    }
}