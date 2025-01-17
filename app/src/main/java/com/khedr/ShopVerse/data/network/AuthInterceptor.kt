package com.khedr.ShopVerse.data.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private var authToken: String? = null, private var language: String? = null) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        authToken?.let {
            requestBuilder.addHeader("Authorization", it)
        }

        requestBuilder.addHeader("Content-Type", "application/json")

        language?.let {
            requestBuilder.addHeader("lang", it)
        }
        return chain.proceed(requestBuilder.build())
    }


    fun setToken(token: String) {
        authToken = token
    }
    fun getToken() :String?{
        return authToken
    }

    fun setLanguage(lang: String) {
        language = lang
    }
}
