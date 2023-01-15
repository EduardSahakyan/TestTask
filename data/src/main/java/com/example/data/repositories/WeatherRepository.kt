package com.example.data.repositories

import com.example.data.api.models.WeatherModel
import retrofit2.Response

interface WeatherRepository {

    suspend fun getWeather(city: String, date: String): Response<WeatherModel>
}