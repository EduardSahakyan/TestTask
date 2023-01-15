package com.example.data.datasources.remote

import com.example.data.api.WeatherResource
import com.example.data.api.models.WeatherModel
import retrofit2.Response
import javax.inject.Inject

internal class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherResource: WeatherResource
): WeatherRemoteDataSource {

    override suspend fun getWeather(city: String, date: String): Response<WeatherModel> {
        return weatherResource.getWeather(city, date)
    }

}