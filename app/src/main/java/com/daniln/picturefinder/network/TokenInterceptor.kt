package com.daniln.picturefinder.network

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
//    todo get token from another place
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