package com.example.data.mapper

import com.example.data.entity.locationMock
import com.example.data.entity.locationMockEmpty
import com.example.data.entity.locationMockNull
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class LocationMapperTest {


    @Before
    fun setUp() {
    }

    @Test
    fun `mapToLocation should map entity to model`() {
        val model = maplocation(locationMock)

        assertEquals(
            model.key,
            locationMock.key
        )
        assertEquals(
            model.englishName,
            locationMock.englishName
        )
    }

    @Test
    fun `mapToLocation should map empty entity to default`() {
        val model = maplocation(locationMockEmpty)

        assertEquals(
            model.key,
            locationMockEmpty.key
        )
        assertEquals(
            model.englishName,
            locationMockEmpty.englishName
        )
    }

    @Test
    fun `mapToLocation should map null entity to default`() {
        val model = maplocation(locationMockNull)

        assertEquals(
            model.key,
            ""
        )
        assertEquals(
            model.englishName,
            ""
        )
    }
}