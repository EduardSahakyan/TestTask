package com.example.testtask.di

import com.example.data.di.DataSourceModule
import com.example.data.di.LocalModule
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        LocalModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        AppModule::class
    ]
)
interface AppComponent : BaseAppComponent
