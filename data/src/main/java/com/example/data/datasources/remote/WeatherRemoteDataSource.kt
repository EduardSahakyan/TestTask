package com.example.data.datasources.remote

import com.example.data.api.models.WeatherModel
import retrofit2.Response

interface WeatherRemoteDataSource {

    suspend fun getWeather(city: String, date: String): Response<WeatherModel>
}