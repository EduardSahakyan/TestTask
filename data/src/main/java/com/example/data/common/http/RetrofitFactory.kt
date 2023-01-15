package com.example.data.common.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal class RetrofitFactory {

    fun newInstance(
        baseUrl: String,
        httpClient: OkHttpClient,
        convertorFactoryProvider: RetrofitConvertorFactoryProvider
    ): Retrofit {
        return Retrofit.Builder()
            .validateEagerly(true)
            .baseUrl(baseUrl)
            .addConverterFactory(convertorFactoryProvider.get())
            .client(httpClient)
            .build()
    }

}