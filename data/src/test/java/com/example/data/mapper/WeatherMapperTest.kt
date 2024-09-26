package com.example.data.mapper

import com.example.data.entity.*
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class WeatherMapperTest {


    @Before
    fun setUp() {
    }

    @Test
    fun `mapToWeather should map entity to model`() {
        val model = mapWeather(weatherMock)

        assertEquals(
            model.weatherIcon,
            weatherMock.weatherIcon
        )
        assertEquals(
            model.weatherText,
            weatherMock.weatherText
        )
        assertEquals(
            model.temperature?.metric?.unit,
            weatherMock.temperature?.metric?.unit
        )
        assertEquals(
            model.temperature?.metric?.value,
            weatherMock.temperature?.metric?.value
        )
    }

    @Test
    fun `mapToWeather should map empty entity to default`() {
        val model = mapWeather(weatherMockEmpty)

        assertEquals(
            model.weatherIcon,
            weatherMockEmpty.weatherIcon
        )
        assertEquals(
            model.weatherText,
            weatherMockEmpty.weatherText
        )
        assertEquals(
            model.temperature?.metric?.unit,
            weatherMockEmpty.temperature?.metric?.unit
        )
        assertEquals(
            model.temperature?.metric?.value,
            weatherMockEmpty.temperature?.metric?.value
        )
    }

    @Test
    fun `mapToWeather should map null entity to default`() {
        val model = mapWeather(weatherMockNull)

        assertEquals(
            model.weatherIcon,
            -1
        )
        assertEquals(
            model.weatherText,
            ""
        )
        assertEquals(
            model.temperature,
            null
        )
    }
}