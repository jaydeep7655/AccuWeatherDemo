package com.example.data.repository

import com.example.data.source.remote.LocationRemoteSource
import com.example.domain.model.Location
import com.example.domain.repository.LocationSearchRepository

class LocationSearchImpl(private val remoteSource: LocationRemoteSource) : LocationSearchRepository {
    override suspend fun getlocations(apiKey: String): List<Location> {
       return  remoteSource.getLocation(apiKey)
    }
}