package com.matt.authinterceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor constructor(private val headerWriter: HeaderWriter) : Interceptor {

    fun withDefault(defaultWithToken: Boolean): AuthInterceptor {
        headerWriter.defaultWithToken = defaultWithToken
        return this
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(headerWriter.modifyRequest(chain.request()).build())
    }

    companion object {
        @JvmStatic
        fun create(repository: TokenStore): AuthInterceptor {
            return AuthInterceptor(HeaderWriter(repository))
        }
    }
}
