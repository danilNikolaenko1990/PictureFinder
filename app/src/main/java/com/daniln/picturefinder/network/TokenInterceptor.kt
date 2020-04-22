package com.daniln.picturefinder.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    //put your unsplash api token here
    var authToken = "Client-ID p1q70LZTYFVwtxi_e64BaoQBtDj9Xxv38SOChFBHqHY"
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", authToken)
                .build()
        )
    }
}