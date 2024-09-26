package com.example.accuweatherdemo.presentation.searchlocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Location
import com.example.domain.usecase.LocationSearchUseCase
import com.example.accuweatherdemo.utils.Resource
import com.example.accuweatherdemo.utils.Status
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchLocationViewModel constructor(private val locationSearchUseCase: LocationSearchUseCase) :
    ViewModel() {

    private val _locationListState = MutableLiveData<Resource<List<Location>>>()
    val locationListState: LiveData<Resource<List<Location>>> = _locationListState

    var alLocationList = ArrayList<Location>()

    fun listLocation(apiKey: String) {
        locationSearchUseCase(apiKey).map {
            when (it.status) {
                Status.LOADING -> {
                    _locationListState.value = Resource.loading(null)
                }
                Status.ERROR -> {
                    _locationListState.value = Resource.error(it.message ?: "", null)
                }
                Status.SUCCESS -> {
                    _locationListState.value = it
                }
            }
        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = SearchLocationViewModel::class.java.name
    }
}