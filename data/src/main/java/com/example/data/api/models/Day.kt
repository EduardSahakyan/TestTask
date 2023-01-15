package com.example.data.api.models

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgtemp_c")
    val temp: Double,
    @SerializedName("condition")
    val condition: Condition
)
