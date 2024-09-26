package com.example.accuweatherdemo.di

import com.example.data.repository.LocationSearchImpl
import com.example.data.source.remote.ApiService
import com.example.domain.repository.LocationSearchRepository
import com.example.domain.usecase.LocationSearchUseCase
import com.example.accuweatherdemo.presentation.searchlocation.SearchLocationViewModel
import com.example.accuweatherdemo.presentation.weatherDetails.WeatherDetailViewModel
import com.example.data.repository.WeatherImpl
import com.example.data.source.remote.LocationRemoteSource
import com.example.data.source.remote.WeatherRemoteSource
import com.example.domain.repository.WeatherRepository
import com.example.domain.usecase.WeatherDisplayUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val AppModule = module {

    factory { LocationRemoteSource(get()) }
    factory { WeatherRemoteSource(get()) }

    single { createLocationSearchUseCase(get()) }
    single { createLocationSearchRepository(get()) }

    single { createWeatherRepository(get()) }
    single { createWeatherUseCase(get()) }

    viewModel { SearchLocationViewModel(get()) }
    viewModel { WeatherDetailViewModel(get()) }
}

fun createLocationSearchRepository(locationRemoteSource: LocationRemoteSource): LocationSearchRepository {
    return LocationSearchImpl(locationRemoteSource)
}

fun createLocationSearchUseCase(locationSearchRepository: LocationSearchRepository): LocationSearchUseCase {
    return LocationSearchUseCase(locationSearchRepository)
}

fun createWeatherRepository(weatherRemoteSource: WeatherRemoteSource): WeatherRepository {
    return WeatherImpl(weatherRemoteSource)
}

fun createWeatherUseCase(weatherRepository: WeatherRepository): WeatherDisplayUseCase {
    return WeatherDisplayUseCase(weatherRepository)
}
