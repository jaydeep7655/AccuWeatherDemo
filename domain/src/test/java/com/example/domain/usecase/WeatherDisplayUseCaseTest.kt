package com.example.domain.usecase

import com.example.accuweatherdemo.utils.Resource
import com.example.domain.model.Weather
import com.example.domain.model.weatherListMock
import com.example.domain.repository.WeatherRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
@RunWith(MockitoJUnitRunner::class)
class WeatherDisplayUseCaseTest {
    private val mockKey = "dhshfsdfsdsdfjkh"
    private val mockCity = "202396"

    @Mock
    private lateinit var repository: WeatherRepository

    private lateinit var useCase: WeatherDisplayUseCase
    private lateinit var result: Resource<List<Weather>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = WeatherDisplayUseCase(repository)
    }

    @Test
    fun `GetWeather Should return list of Weather on success`() {
        runBlocking {
            whenever(
                repository.getWeather(mockKey, mockCity)
            ).thenReturn(
                weatherListMock
            )

            useCase(mockKey, mockCity).collect {
                result = it
            }
            val model = weatherListMock
            assertNotNull(result)
            assertEquals(result.data?.get(0)?.weatherText, model.get(0).weatherText)
            assertEquals(result.data?.get(0)?.weatherIcon, model.get(0).weatherIcon)
        }
    }

}