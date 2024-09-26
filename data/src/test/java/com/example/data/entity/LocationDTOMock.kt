package com.example.data.entity

import com.example.domain.model.Location
import org.junit.Assert.*


val locationMock = LocationDTO(
    dataSets = arrayListOf(
        "AirQualityCurrentConditions",
        "AirQualityForecasts",
        "FutureRadar",
        "MinuteCast"
    ),
    englishName = "Delhi",
    isAlias = false,
    key = "28143",
    localizedName = "Delhi",
    primaryPostalCode = "380015",
    rank = 1,
    supplementalAdminAreas = null,
    type = "city",
    version = 1
)

val locationMockEmpty = LocationDTO()

val locationMockNull: LocationDTO? = null

val locationResultMock = Location(
    englishName = "Delhi",
    key = "28143",
)

val locationresultListMock = listOf<Location>(locationResultMock)


