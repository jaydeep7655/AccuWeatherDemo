package com.example.data.source.remote

import com.example.data.mapper.mapWeather
import com.example.domain.model.Weather

class WeatherRemoteSource(private val apiService: ApiService) {
    suspend fun getWeather(locationId: String, apiKey: String): List<Weather> =
        apiService.getWeather(locationId, apiKey).map { mapWeather(it) }
}