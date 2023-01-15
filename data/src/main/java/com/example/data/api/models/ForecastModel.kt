package com.example.data.api.models

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>
)
