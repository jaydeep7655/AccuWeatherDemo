package com.example.data.entity

import com.example.domain.model.Weather

val metricMock = WeatherDTO.TemperatureDTO.MetricDTO(
    value = 26.1,
    unit = "C",
    unitType = 17
)

val imperialMock = WeatherDTO.TemperatureDTO.ImperialDTO(
    value = 79,
    unit = "F",
    unitType = 18
)

val temperatureMock = WeatherDTO.TemperatureDTO(
    metric = metricMock,
    imperial = null
)

val weatherMock = WeatherDTO(
    temperature = temperatureMock,
    weatherText = "Cloudy",
    localObservationDateTime = "2022-10-06T22:03:00+05:30",
    epochTime = 1665073980,
    weatherIcon = 7,
    hasPrecipitation = false,
    precipitationType = null,
    isDayTime = false,
    mobileLink = "http://www.accuweather.com/en/in/delhi/202396/current-weather/202396?lang=en-us",
    link = "http://www.accuweather.com/en/in/delhi/202396/current-weather/202396?lang=en-us"
)

val weatherMockEmpty = WeatherDTO()

val weatherMockNull  = null

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

val weatherResultListMock = listOf<Weather>(weatherResultMock)