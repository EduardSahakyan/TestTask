package com.example.domain.di

import com.example.domain.usecases.AddEventUseCase
import com.example.domain.usecases.AddEventUseCaseImpl
import com.example.domain.usecases.GetEventsUseCase
import com.example.domain.usecases.GetEventsUseCaseImpl
import com.example.domain.usecases.RemoveEventUseCaseImpl
import com.example.domain.usecases.RemoveEventUseCase
import com.example.domain.usecases.GetWeatherUseCaseImpl
import com.example.domain.usecases.GetWeatherUseCase
import dagger.Binds
import dagger.Module

@Module(includes = [UseCaseModule.Definitions::class])
object UseCaseModule {

    @Module
    internal interface Definitions {

        @Binds
        fun bindAddEventUseCase(impl: AddEventUseCaseImpl): AddEventUseCase

        @Binds
        fun bindGetEventsUseCase(impl: GetEventsUseCaseImpl): GetEventsUseCase

        @Binds
        fun bindRemoveEventUseCase(impl: RemoveEventUseCaseImpl): RemoveEventUseCase

        @Binds
        fun bindGetWeatherUseCase(impl: GetWeatherUseCaseImpl): GetWeatherUseCase

    }

}