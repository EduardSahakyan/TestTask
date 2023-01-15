package com.example.data.common.http

import retrofit2.Converter

interface RetrofitConvertorFactoryProvider {
    fun get(): Converter.Factory
}