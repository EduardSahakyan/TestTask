package com.example.testtask.di

import android.app.Application
import com.example.data.di.DataSourceModule
import com.example.data.di.LocalModule
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.UseCaseModule
import com.example.testtask.home.HomeFragment
import com.example.testtask.app.App
import com.example.testtask.event.EventFragment

object Injector : BaseAppComponent {

    private lateinit var appComponent: AppComponent

    @Suppress("DEPRECATION")
    fun buildGraph(application: Application) {
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule)
            .localModule(LocalModule)
            .dataSourceModule(DataSourceModule)
            .repositoryModule(RepositoryModule)
            .useCaseModule(UseCaseModule)
            .appModule(AppModule(application))
            .build()
    }

    override fun inject(target: App) {
        appComponent.inject(target)
    }

    override fun inject(target: HomeFragment) {
        appComponent.inject(target)
    }

    override fun inject(target: EventFragment) {
        appComponent.inject(target)
    }
}