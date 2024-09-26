package com.example.data.mapper

import com.example.data.entity.LocationDTO
import com.example.domain.model.Location

fun maplocation(locationDTO: LocationDTO?): Location {
    if (locationDTO != null) {
        return Location(
            key = locationDTO.key,
            englishName = locationDTO.englishName
        )
    } else {
        return Location(
            key = "",
            englishName = ""
        )
    }
}