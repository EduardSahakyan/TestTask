package com.example.data.common.http

import com.example.data.common.AppEnvironment
import retrofit2.Retrofit
import kotlin.reflect.KClass

internal class RetrofitResourceFactory (
    env: AppEnvironment,
    httpFactory: HttpClientFactory,
    retrofitFactory: RetrofitFactory,
    convertorFactoryProvider: RetrofitConvertorFactoryProvider
) {
    private val retrofit: Retrofit

    init {
        val httpClient = httpFactory.newInstance(
            interceptors = arrayOf(
                httpFactory.createAuthInterceptor(env),
                httpFactory.createContentTypeInterceptor(),
                httpFactory.createLoggingInterceptor()
            )
        )
        retrofit = retrofitFactory.newInstance(env.baseUrl, httpClient, convertorFactoryProvider)
    }

    fun <T: Any> create(cls: KClass<T>): T {
        return retrofit.create(cls.java)
    }
}