package com.example.data.common.http

import com.example.data.common.AppEnvironment
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

internal object HttpClientFactory {

    private const val TIMEOUT_MINUTES = 1L

    fun newInstance(cache: Cache? = null, networkInterceptor: Interceptor? = null, vararg interceptors: Interceptor?): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
        builder.readTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
        builder.writeTimeout(TIMEOUT_MINUTES, TimeUnit.MINUTES)
        if (cache != null) {
            builder.cache(cache)
        }
        for (interceptor in interceptors) {
            interceptor?.let { builder.addInterceptor(interceptor) }
        }
        if (networkInterceptor != null) {
            builder.addNetworkInterceptor(networkInterceptor)
        }
        return builder.build()
    }

    fun createAuthInterceptor(env: AppEnvironment): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val internalRequest = request.url.toString().contains(env.baseUrl)
            if (internalRequest) {
                request = chain.request().newBuilder()
                    .addHeader(Constants.HEADER_AUTH_TOKEN, Constants.TOKEN)
                    .build()
            }
            return@Interceptor chain.proceed(request)
        }
    }

    fun createLoggingInterceptor(): Interceptor {
        return object : Interceptor {

            private val bodyInterceptor: HttpLoggingInterceptor
            private val headerInterceptor: HttpLoggingInterceptor

            init {
                val logFunction = { message: String -> Timber.tag("OkHttp").v(message) }
                bodyInterceptor = HttpLoggingInterceptor(logFunction)
                headerInterceptor = HttpLoggingInterceptor(logFunction)
                bodyInterceptor.level = HttpLoggingInterceptor.Level.BODY
                headerInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
            }

            override fun intercept(chain: Interceptor.Chain): Response {
                val contentType = chain.request().body?.contentType()
                if (contentType != null && Constants.TYPE_MULTIPART == contentType.type) {
                    return headerInterceptor.intercept(chain)
                }
                return bodyInterceptor.intercept(chain)
            }
        }
    }

    fun createContentTypeInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(Constants.HEADER_CONTENT_TYPE, Constants.CONTENT_TYPE_JSON)
                .build()
            return@Interceptor chain.proceed(request)
        }
    }
}