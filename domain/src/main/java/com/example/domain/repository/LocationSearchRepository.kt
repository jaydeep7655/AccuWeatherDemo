package com.example.domain.repository

import com.example.domain.model.Location

interface LocationSearchRepository {
    suspend fun getlocations(apiKey:String) : List<Location>
}