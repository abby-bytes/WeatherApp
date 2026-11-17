package com.example.weatherapp.ui.theme.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun Forecasts(viewModel: MainViewModel) {
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
    ) {
        weather?.forecast?.forecastDay?.let { forecastDays ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(forecastDays) { dayItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(145.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Parse into Day Name ("Today", "Tuesday") and Date ("Nov 16, 2026")
                            val (dayName, formattedDate) = try {
                                val parsed = LocalDate.parse(dayItem.date)
                                val today = LocalDate.now()
                                val day = if (parsed == today) "Today" else parsed.format(DateTimeFormatter.ofPattern("EEEE", Locale.getDefault()))
                                val date = parsed.format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.getDefault()))
                                day to date
                            } catch (_: Exception) {
                                dayItem.date to ""
                            }

                            // Left side: Day, Date, and Condition
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = dayName,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                                if (formattedDate.isNotEmpty()) {
                                    Text(
                                        text = formattedDate,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = dayItem.day.condition.text,
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                )
                            }

                            val iconRes = getWeatherDrawable(dayItem.day.condition.text)
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = dayItem.day.condition.text,
                                modifier = Modifier.size(68.dp)
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            // Right side: High / Low Temperatures + Humidity
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "${dayItem.day.maxTempC.toInt()}° /${dayItem.day.minTempC.toInt()}°",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Humidity ${dayItem.day.avgHumidity.toInt()}%",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}