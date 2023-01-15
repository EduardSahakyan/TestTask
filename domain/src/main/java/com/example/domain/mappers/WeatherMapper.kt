package com.example.domain.mappers

import com.example.data.api.models.WeatherModel
import com.example.domain.entities.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() : ApiMapper<WeatherModel, Weather> {

    override fun fromModel(data: WeatherModel): Weather {
        return Weather(
            temperature = data.forecast.forecastDay[0].day.temp,
            iconUrl = data.forecast.forecastDay[0].day.condition.icon
        )
    }

    override fun fromModelList(listData: List<WeatherModel>): List<Weather> {
        val mappedList = mutableListOf<Weather>()
        listData.forEach {
            mappedList.add(fromModel(it))
        }
        return mappedList
    }
}