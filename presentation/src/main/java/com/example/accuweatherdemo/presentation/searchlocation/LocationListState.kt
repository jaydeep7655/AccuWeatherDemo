package com.example.accuweatherdemo.presentation.searchlocation

import com.example.domain.model.Location

data class LocationListState(
    var data: List<Location>? = null,
    var error: String = "",
    var isLoading: Boolean = false
)
