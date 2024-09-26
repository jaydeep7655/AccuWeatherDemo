package com.example.accuweatherdemo.presentation.searchlocation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.post.domain.exception.traceErrorException
import com.example.accuweatherdemo.presentation.MainCoroutineScopeRule
import com.example.accuweatherdemo.utils.Resource
import com.example.accuweatherdemo.utils.Status
import com.example.domain.model.Location
import com.example.domain.usecase.LocationSearchUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNull

@RunWith(MockitoJUnitRunner::class)
class SearchLocationViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private val mockKey = "dhshfsdfsdsdfjkh"
    private lateinit var viewModel: SearchLocationViewModel

    @Mock
    private lateinit var useCase: LocationSearchUseCase

    @Mock
    private lateinit var result: List<Location>

    @Before
    fun setup() {
        viewModel = SearchLocationViewModel(useCase)
    }

    @Test
    fun `ListLocation Should return list of Location on success`() =
        coroutineScope.runBlockingTest {
            val flow = flow {
                emit(Resource.loading(null))
                delay(10)
                emit(Resource.success(result))
            }

            whenever(useCase(mockKey)).thenReturn(flow)
            viewModel.listLocation(mockKey)
            delay(200L)
            Assertions.assertEquals(viewModel.locationListState.value?.status, Status.SUCCESS)
            kotlin.test.assertNotNull(viewModel.locationListState.value?.data)
        }

    @Test
    fun `ListLocation Should error on failure`() =
        coroutineScope.runBlockingTest {
            val flow = flow {
                emit(Resource.loading(null))
                delay(10)
                emit(Resource.error(traceErrorException(Exception()).message, null))
            }

            whenever(useCase(mockKey)).thenReturn(flow)
            viewModel.listLocation(mockKey)
            delay(200L)
            Assertions.assertEquals(viewModel.locationListState.value?.status, Status.ERROR)
            assertNull(viewModel.locationListState.value?.data)
        }
}