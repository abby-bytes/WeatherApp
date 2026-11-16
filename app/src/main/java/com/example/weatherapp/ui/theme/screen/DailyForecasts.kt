package com.example.weatherapp.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun DailyForecast(viewModel: MainViewModel) {
    val weather = viewModel.weather

    Box(
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
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        weather?.let { data ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Formatted day & date (e.g., "Monday, Nov 16")
                val formattedDate = try {
                    val parsedDate = LocalDate.parse(data.forecast?.forecastDay?.firstOrNull()?.date ?: "")
                    parsedDate.format(DateTimeFormatter.ofPattern("EEEE, MMM d", Locale.getDefault()))
                } catch (e: Exception) {
                    "Today"
                }

                Text(
                    text = formattedDate,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Weather condition icon (Local Drawable)
                val iconRes = getWeatherDrawable(data.current.condition.text)
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = data.current.condition.text,
                    modifier = Modifier.size(160.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Huge Temperature Display
                Text(
                    text = "${data.current.tempCelsius.toInt()}°C",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = data.current.condition.text,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Details Card (Wind & Humidity) with soft transparent background to blend with gradient
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Wind", fontSize = 14.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${data.current.windKph} km/h", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Humidity", fontSize = 14.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "${data.current.humidity}%", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                    }
                }
            }
        } ?: Text(text = "Loading weather...", color = Color.Gray, fontSize = 16.sp)
    }
}

// Helper function to map weather text to local PNG drawables (Restored so the error goes away!)
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