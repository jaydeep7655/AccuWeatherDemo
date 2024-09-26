package com.example.data.mapper

import com.example.data.entity.WeatherDTO
import com.example.domain.model.Weather

fun mapWeather(weatherDTO: WeatherDTO?): Weather {
    if(weatherDTO != null) {
        return Weather(
            weatherIcon = weatherDTO.weatherIcon,
            weatherText = weatherDTO.weatherText,
            temperature = weatherDTO.temperature?.toDomainTemperature()
        )
    }else{
        return Weather(
            weatherIcon = -1,
            weatherText = "",
            temperature = null
        )
    }
}

fun WeatherDTO.TemperatureDTO.toDomainTemperature(): Weather.Temperature {
    return Weather.Temperature(
        metric = this.metric?.toDomainMetric()
    )
}

fun WeatherDTO.TemperatureDTO.MetricDTO.toDomainMetric(): Weather.Temperature.Metric {
    return Weather.Temperature.Metric(
        unit = this.unit,
        value = this.value
    )
}