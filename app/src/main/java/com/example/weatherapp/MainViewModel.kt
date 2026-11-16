package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherApi
import com.example.weatherapp.data.dao.Weather
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var weather by mutableStateOf<Weather?>(null)
        private set

    var location by mutableStateOf<Location?>(null)
        private set

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)

    private val api = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    @SuppressLint("MissingPermission")
    fun refreshLocation() {
        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { result ->
                if (result != null) {
                    location = result
                    val latlng = "${result.latitude},${result.longitude}"
                    Log.i("MainViewModel", "Phone is located at $latlng.")

                    // Fetch live weather using the device's coordinates!
                    getWeather(latlng)
                }
            }
    }

    fun getWeather(query: String) {
        viewModelScope.launch {
            try {
                weather = api.getWeather(q = query)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}