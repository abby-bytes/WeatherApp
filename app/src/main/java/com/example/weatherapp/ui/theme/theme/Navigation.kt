package com.example.weatherapp.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.Screen.DailyForecast
import com.example.weatherapp.ui.theme.Screen.Forecasts


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) } // 0 = Now, 1 = Daily

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Halifax, Nova Scotia")
                }
            )
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                // Tab 0 (Current Weather)
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "Now") },
                    label = { Text("Now") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )

                // Tab 1 Daily Forecasts
                NavigationBarItem(
                    icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = "Daily") },
                    label = { Text("Daily") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedIndex) {
                0 -> DailyForecast()
                1 -> Forecasts()
            }
        }
    }
}