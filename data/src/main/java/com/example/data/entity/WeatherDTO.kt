package com.example.data.entity


import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("EpochTime")
    val epochTime: Int? = -1,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean? = false,
    @SerializedName("IsDayTime")
    val isDayTime: Boolean? = false,
    @SerializedName("Link")
    val link: String? = "",
    @SerializedName("LocalObservationDateTime")
    val localObservationDateTime: String? = "",
    @SerializedName("MobileLink")
    val mobileLink: String? = "",
    @SerializedName("PrecipitationType")
    val precipitationType: Any? = null,
    @SerializedName("Temperature")
    val temperature: TemperatureDTO? = null,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int? = -1,
    @SerializedName("WeatherText")
    val weatherText: String? = ""
) {
    data class TemperatureDTO(
        @SerializedName("Imperial")
        val imperial: ImperialDTO?,
        @SerializedName("Metric")
        val metric: MetricDTO?
    ) {
        data class ImperialDTO(
            @SerializedName("Unit")
            val unit: String? = "",
            @SerializedName("UnitType")
            val unitType: Int?,
            @SerializedName("Value")
            val value: Int?
        )

        data class MetricDTO(
            @SerializedName("Unit")
            val unit: String? = "",
            @SerializedName("UnitType")
            val unitType: Int? = -1,
            @SerializedName("Value")
            val value: Double? = 0.0
        )
    }
}