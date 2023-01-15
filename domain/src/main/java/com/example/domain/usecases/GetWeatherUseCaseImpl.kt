package com.example.domain.usecases

import com.example.data.Resource
import com.example.data.repositories.WeatherRepository
import com.example.data.safeApiCall
import com.example.domain.entities.Weather
import com.example.domain.mappers.WeatherMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val weatherMapper: WeatherMapper
) : GetWeatherUseCase {

    override fun invoke(city: String, date: String): Flow<Resource<Weather>> {
        return safeApiCall(weatherMapper::fromModel) { weatherRepository.getWeather(city, date) }
    }

}