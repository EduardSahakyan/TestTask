package com.example.testtask.app

import android.app.Application
import com.example.testtask.di.Injector

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.buildGraph(this)
        Injector.inject(this)
    }

}