package com.example.domain.usecase

import com.example.accuweatherdemo.utils.Resource
import com.example.domain.model.Location
import com.example.domain.model.locationListMock
import com.example.domain.repository.LocationSearchRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationSearchUseCaseTest {
    private val mockKey = "dhshfsdfsdsdfjkh"

    @Mock
    private lateinit var repository: LocationSearchRepository

    private lateinit var useCase: LocationSearchUseCase
    private lateinit var result: Resource<List<Location>>

    @Before
    fun setup() {
        useCase = LocationSearchUseCase(repository)
    }

    @Test
    fun `GetLocation Should return list of Location on success`() {
        runBlocking {
            whenever(
                repository.getlocations(mockKey)
            ).thenReturn(
                locationListMock
            )

            useCase(mockKey).collect {
                result = it
            }
            val model = locationListMock
            assertNotNull(result)
            assertEquals(result.data?.get(0)?.key, model.get(0).key)
            assertEquals(result.data?.get(0)?.englishName, model.get(0).englishName)
        }
    }

}