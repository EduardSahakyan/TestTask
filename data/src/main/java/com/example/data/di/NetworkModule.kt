package com.example.data.di

import com.example.data.api.WeatherResource
import com.example.data.common.AppEnvironment
import com.example.data.common.http.HttpClientFactory
import com.example.data.common.http.RetrofitConvertorFactoryProvider
import com.example.data.common.http.RetrofitFactory
import com.example.data.common.http.RetrofitResourceFactory
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofitConvertorFactoryProvider(): RetrofitConvertorFactoryProvider {
        return object : RetrofitConvertorFactoryProvider {
            override fun get(): Converter.Factory {
                return GsonConverterFactory.create()
            }
        }
    }

    @Provides
    @Singleton
    internal fun provideRetrofitFactory(): RetrofitFactory = RetrofitFactory()

    @Provides
    @Singleton
    internal fun provideResourceFactory(
        env: AppEnvironment,
        retrofitFactory: RetrofitFactory,
        convertorFactoryProvider: RetrofitConvertorFactoryProvider
    ): RetrofitResourceFactory = RetrofitResourceFactory(
        env,
        HttpClientFactory,
        retrofitFactory, convertorFactoryProvider
    )

    @Provides
    @Singleton
    internal fun provideWeatherResource(resFactory: RetrofitResourceFactory): WeatherResource =
        resFactory.create(WeatherResource::class)
}