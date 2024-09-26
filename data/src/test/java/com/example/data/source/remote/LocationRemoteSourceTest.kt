package com.example.data.source.remote

import com.example.data.entity.locationresultListMock
import com.example.data.repository.LocationSearchImpl
import com.example.domain.repository.LocationSearchRepository
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
class LocationRemoteSourceTest{
    private val mockKey = "dhshfsdfsdsdfjkh"
    lateinit var repository: LocationSearchRepository

    @Mock
    lateinit var remoteSource: LocationRemoteSource

    @BeforeEach
    fun setup() {
        repository = LocationSearchImpl(remoteSource)
    }

    @Test
    fun `GetLocation Should return success`() {
        runBlocking {
            whenever(
                remoteSource.getLocation(mockKey)
            ).thenReturn(
                locationresultListMock
            )

            val result = remoteSource.getLocation(mockKey)
            verify(remoteSource).getLocation(mockKey)
            assertNotNull(result)
            assertEquals(locationresultListMock.get(0).key, result.get(0).key)
            assertEquals(locationresultListMock.get(0).englishName, result.get(0).englishName)
        }
    }
}