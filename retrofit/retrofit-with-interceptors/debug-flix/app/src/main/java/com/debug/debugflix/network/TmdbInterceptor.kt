package com.debug.debugflix.network

import com.debug.debugflix.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class TmdbInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.tokenTMDB)
            .build()

        val newHeaders = originalRequest.headers.newBuilder().build()

        val response = chain.proceed(
            chain.request().newBuilder()
                .url(newUrl)
                .headers(newHeaders)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build()
        )

        val responseBody = response.body
        val responseHeaders = response.headers

        val newResponse = response.newBuilder()
            .body(responseBody)
            .headers(responseHeaders)
            .code(200)
            .build()

        return newResponse
    }

}
