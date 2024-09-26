package com.example.data.source.remote

import com.example.data.entity.weatherResultListMock
import com.example.data.repository.WeatherImpl
import com.example.domain.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherRemoteSourceTest {
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
    fun `GetWeather should return success`() {
        runBlocking {
            whenever(
                remoteSource.getWeather(mockCity, mockKey)
            ).thenReturn(
                weatherResultListMock
            )

            val result = remoteSource.getWeather(mockCity, mockKey)
            verify(remoteSource).getWeather(mockCity, mockKey)
            assertNotNull(result)
            assertEquals(weatherResultListMock.get(0).weatherText, result.get(0).weatherText)
            assertEquals(weatherResultListMock.get(0).weatherIcon, result.get(0).weatherIcon)
        }
    }
}