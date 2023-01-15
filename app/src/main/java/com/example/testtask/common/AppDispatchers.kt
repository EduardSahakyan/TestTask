package com.example.testtask.common

import kotlinx.coroutines.CoroutineDispatcher

class AppDispatchers(
    val ioDispatcher: CoroutineDispatcher,
    val mainDispatcher: CoroutineDispatcher,
    val defaultDispatcher: CoroutineDispatcher,
    val unconfinedDispatcher: CoroutineDispatcher
)