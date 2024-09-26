package com.example.data.repository

import com.example.data.mapper.mapWeather
import com.example.data.source.remote.ApiService
import com.example.data.source.remote.WeatherRemoteSource
import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository

class WeatherImpl(private val weatherRemoteSource: WeatherRemoteSource) : WeatherRepository {
    override suspend fun getWeather(apiKey: String, locationId: String): List<Weather> {
        return weatherRemoteSource.getWeather(locationId, apiKey)
    }
}