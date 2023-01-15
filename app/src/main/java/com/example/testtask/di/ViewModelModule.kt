package com.example.testtask.di

import androidx.lifecycle.ViewModel
import com.example.testtask.home.HomeViewModel
import com.example.testtask.di.annotations.ViewModelKey
import com.example.testtask.event.EventViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule.Definitions::class])
object ViewModelModule {

    @Module
    internal interface Definitions {

        @Binds
        @IntoMap
        @ViewModelKey(HomeViewModel::class)
        fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

        @Binds
        @IntoMap
        @ViewModelKey(EventViewModel::class)
        fun bindEventViewModel(viewModel: EventViewModel): ViewModel

    }

}