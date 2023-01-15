package com.example.data.repositories

import com.example.data.datasources.remote.WeatherRemoteDataSource
import com.example.data.api.models.WeatherModel
import retrofit2.Response
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getWeather(city: String, date: String): Response<WeatherModel> {
        return weatherRemoteDataSource.getWeather(city, date)
    }

}