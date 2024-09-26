package com.example.data.repository

import com.example.data.entity.locationresultListMock
import com.example.data.entity.weatherResultListMock
import com.example.data.source.remote.LocationRemoteSource
import com.example.data.source.remote.WeatherRemoteSource
import com.example.domain.repository.LocationSearchRepository
import com.example.domain.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class WeatherImplTest {

    private val mockKey = "dhshfsdfsdsdfjkh"
    private val mockCity = "202396"
    lateinit var repository: WeatherRepository

    @Mock
    lateinit var remoteSource: WeatherRemoteSource

    @BeforeEach
    fun setup() {
        repository = WeatherImpl(remoteSource)
    }

    @Test
    fun `Should fetch weather`() {
        runBlocking {
            whenever(
                remoteSource.getWeather(mockCity, mockKey)
            ).thenReturn(
                weatherResultListMock
            )

            val result = remoteSource.getWeather(mockCity, mockKey)
            verify(remoteSource).getWeather(mockCity, mockKey)
            assertEquals(result, weatherResultListMock)
        }
    }
}