package com.example.testtask.di

import com.example.testtask.home.HomeFragment
import com.example.testtask.app.App
import com.example.testtask.event.EventFragment

interface BaseAppComponent {
    fun inject(target: App)
    fun inject(target: HomeFragment)
    fun inject(target: EventFragment)
}