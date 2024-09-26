package com.example.data.source.remote

import com.example.data.entity.LocationDTO
import com.example.data.entity.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("locations/v1/topcities/50")
    suspend fun getLocations(@Query("apikey") apiKey : String) : List<LocationDTO>

    @GET("currentconditions/v1/{locationId}")
    suspend fun getWeather(@Path("locationId") locationId:String, @Query("apikey") apiKey : String) : List<WeatherDTO>


}