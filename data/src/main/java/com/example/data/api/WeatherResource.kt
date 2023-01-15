package com.example.data.api

import com.example.data.api.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherResource {

    @GET("future.json")
    suspend fun getWeather(@Query("q") city: String, @Query("dt") date: String): Response<WeatherModel>

}