package com.example.weatherapp.ui.theme.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weatherapp.MainViewModel

@Composable
fun Forecasts(viewModel: MainViewModel) {
    val weather = viewModel.weather

    weather?.forecast?.forecastDay?.let { forecastDays ->
        LazyColumn {
            items(forecastDays) { dayItem ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    Text(
                        text = dayItem.date,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    AsyncImage(
                        model = "https:${dayItem.day.condition.icon}",
                        contentDescription = dayItem.day.condition.text,
                        modifier = Modifier.size(70.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${dayItem.day.maxTempC.toInt()}°C High  ${dayItem.day.minTempC.toInt()}°C Low",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${dayItem.day.condition.text}. Wind ${dayItem.day.maxWindKph}kph. Humidity ${dayItem.day.avgHumidity.toInt()}%.",
                        fontStyle = FontStyle.Italic,
                        fontSize = 13.sp
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}