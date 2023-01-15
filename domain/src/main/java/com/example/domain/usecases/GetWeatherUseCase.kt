package com.example.domain.usecases

import com.example.data.Resource
import com.example.domain.entities.Weather
import kotlinx.coroutines.flow.Flow

interface GetWeatherUseCase {

    operator fun invoke(city: String, date: String): Flow<Resource<Weather>>
}