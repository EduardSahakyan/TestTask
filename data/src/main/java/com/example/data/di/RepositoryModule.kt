package com.example.data.di

import com.example.data.repositories.EventRepository
import com.example.data.repositories.EventRepositoryImpl
import com.example.data.repositories.WeatherRepository
import com.example.data.repositories.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule.Definitions::class])
object RepositoryModule {

    @Module
    internal interface Definitions {

        @Binds
        fun bindEventRepository(impl: EventRepositoryImpl): EventRepository

        @Binds
        fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    }

}