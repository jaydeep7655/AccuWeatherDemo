package com.example.domain.model

data class Weather(
    val temperature: Temperature?,
    val weatherIcon: Int?,
    val weatherText: String?
) {
    data class Temperature(
        val metric: Metric?
    ) {
        data class Metric(
            val unit: String?,
            val value: Double?
        )
    }
}
