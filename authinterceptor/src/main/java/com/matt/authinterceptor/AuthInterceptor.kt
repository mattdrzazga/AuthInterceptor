package com.matt.authinterceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor constructor(private val headerWriter: HeaderWriter) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(headerWriter.modifyRequest(chain.request()).build())
    }
}
