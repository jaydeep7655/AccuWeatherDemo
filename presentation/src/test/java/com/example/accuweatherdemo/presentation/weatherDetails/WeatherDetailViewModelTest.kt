package com.example.accuweatherdemo.presentation.weatherDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.post.domain.exception.traceErrorException
import com.example.accuweatherdemo.di.AppDispatchers
import com.example.accuweatherdemo.presentation.MainCoroutineScopeRule
import com.example.accuweatherdemo.utils.Resource
import com.example.accuweatherdemo.utils.Status
import com.example.domain.model.Weather
import com.example.domain.usecase.WeatherDisplayUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@RunWith(MockitoJUnitRunner::class)
class WeatherDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val mockKey = "dhshfsdfsdsdfjkh"
    private val mockCity = "202396"

    private lateinit var viewModel: WeatherDetailViewModel

    @Mock
    private lateinit var useCase: WeatherDisplayUseCase

    @Mock
    private lateinit var result: List<Weather>

    @Before
    fun setup() {
        viewModel = WeatherDetailViewModel(useCase)
    }

    @Test
    fun `GetWeather Should return list of Weather on success`() =
        coroutineScope.runBlockingTest {
            val flow = flow {
                emit(Resource.loading(null))
                delay(10)
                emit(Resource.success(result))
            }

            whenever(useCase(mockKey, mockCity)).thenReturn(flow)
            viewModel.getWeather(mockKey, mockCity)
            delay(200L)
            assertEquals(viewModel.weatherDetailState.value?.status, Status.SUCCESS)
            assertNotNull(viewModel.weatherDetailState.value?.data)
        }

    @Test
    fun `GetWeather Should error on failure`() =
        coroutineScope.runBlockingTest {
            val flow = flow {
                emit(Resource.loading(null))
                delay(10)
                emit(Resource.error(traceErrorException(Exception()).message, null))
            }

            whenever(useCase(mockKey, mockCity)).thenReturn(flow)
            viewModel.getWeather(mockKey, mockCity)
            delay(200L)
            assertEquals(viewModel.weatherDetailState.value?.status, Status.ERROR)
            assertNull(viewModel.weatherDetailState.value?.data)
        }
}