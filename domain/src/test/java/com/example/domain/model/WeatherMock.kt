package com.example.domain.model

import com.example.domain.model.Weather

val metricMock = Weather.Temperature.Metric(
    value = 26.1,
    unit = "C",
)

val temperatureMock = Weather.Temperature(
    metric = metricMock,
)

val weatherMock = Weather(
    temperature = temperatureMock,
    weatherText = "Cloudy",
    weatherIcon = 7,
)

val metricResultMock = Weather.Temperature.Metric(
    value = 26.1,
    unit = "C",
)

val temperatureResultMock = Weather.Temperature(
    metric = metricResultMock,
)

val weatherResultMock = Weather(
    temperature = temperatureResultMock,
    weatherText = "Cloudy",
    weatherIcon = 7,
)

val weatherListMock = listOf<Weather>(weatherResultMock)