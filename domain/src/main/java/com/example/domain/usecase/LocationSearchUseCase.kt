package com.example.domain.usecase

import com.android.post.domain.exception.traceErrorException
import com.example.accuweatherdemo.utils.Resource
import com.example.domain.model.Location
import com.example.domain.repository.LocationSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class LocationSearchUseCase constructor(private val locationSearchRepository: LocationSearchRepository) {

    operator fun invoke(apiKey: String): Flow<Resource<List<Location>>> =
        flow {
            try {
                emit(Resource.loading(null))
                val result = locationSearchRepository.getlocations(apiKey)
                emit(Resource.success(result))
            } catch (e: Exception) {
                emit(Resource.error(traceErrorException(e).message, null))
            }
        }
}