package com.example.domain.usecase

import com.android.post.domain.exception.traceErrorException
import com.example.accuweatherdemo.utils.Resource
import com.example.domain.model.Weather
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherDisplayUseCase constructor(private val weatherRepository: WeatherRepository) {
    operator fun invoke(apiKey: String, locationId: String): Flow<Resource<List<Weather>>> =
        flow {
            try {
                emit(Resource.loading(null))
                val result = weatherRepository.getWeather(apiKey, locationId)
                emit(Resource.success(result))
            } catch (e: Exception) {
                emit(Resource.error(traceErrorException(e).message, null))
            }
        }
}