package com.example.data.di

import com.example.data.datasources.local.EventLocalDataSource
import com.example.data.datasources.local.EventLocalDataSourceImpl
import com.example.data.datasources.remote.WeatherRemoteDataSource
import com.example.data.datasources.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DataSourceModule.Definitions::class])
object DataSourceModule {

    @Module
    internal interface Definitions {

        @Binds
        fun bindEventLocalDataSource(impl: EventLocalDataSourceImpl): EventLocalDataSource

        @Binds
        fun bindWeatherRemoteDataSource(impl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    }

}