package com.example.accuweatherdemo.presentation.weatherDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accuweatherdemo.di.AppDispatchers
import com.example.accuweatherdemo.utils.Resource
import com.example.accuweatherdemo.utils.Status
import com.example.domain.model.Weather
import com.example.domain.usecase.WeatherDisplayUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class WeatherDetailViewModel constructor(
    private val useCase: WeatherDisplayUseCase
) : ViewModel() {

    private val _weatherDetailState = MutableLiveData<Resource<List<Weather>>>()
    var weatherDetailState: LiveData<Resource<List<Weather>>> = _weatherDetailState

    fun getWeather(apiKey: String, locationId: String) {
        useCase(apiKey, locationId).map {
            when (it.status) {
                Status.LOADING -> {
                    _weatherDetailState.value = Resource.loading(null)
                }
                Status.ERROR -> {
                    _weatherDetailState.value = Resource.error(it.message ?: "", null)
                }
                Status.SUCCESS -> {
                    _weatherDetailState.value = it
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}