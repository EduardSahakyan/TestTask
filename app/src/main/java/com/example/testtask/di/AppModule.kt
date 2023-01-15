package com.example.testtask.di

import android.app.Application
import android.content.Context
import com.example.data.common.AppEnvironment
import com.example.testtask.common.AppDispatchers
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideAppEnvironment(): AppEnvironment = AppEnvironment(Env.BASE_URL)

    @Provides
    @Singleton
    fun provideAppDispatchers(): AppDispatchers = AppDispatchers(Dispatchers.IO, Dispatchers.Main, Dispatchers.Default, Dispatchers.Unconfined)

}