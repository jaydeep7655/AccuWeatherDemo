package com.example.data.source.remote

import com.example.data.mapper.maplocation
import com.example.domain.model.Location

class LocationRemoteSource(private val apiService: ApiService) {
    suspend fun getLocation(apiKey: String): List<Location> =
        apiService.getLocations(apiKey).map { maplocation(it) }
}