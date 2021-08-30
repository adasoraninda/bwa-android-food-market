package com.codetron.foodmarketmvp.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val token: String?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        if (token != null) {
            request.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())
    }

}