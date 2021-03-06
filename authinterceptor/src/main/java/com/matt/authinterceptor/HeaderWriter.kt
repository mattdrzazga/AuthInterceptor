package com.matt.authinterceptor

import okhttp3.Request

class HeaderWriter(private val repository: TokenStore) {
    var defaultWithToken = true

    fun modifyRequest(request: Request): Request.Builder {
        val token = repository.getToken()
        val header = repository.getTokenHeader()
        val builder = request.newBuilder()
        removeAuthHeaders(builder)

        if (hasAuthHeader(request)) {
            return builder.addHeader(header, token)
        }
        if (hasNoAuthHeader(request)) {
            return builder
        }
        if (defaultWithToken) {
            return builder.addHeader(header, token)
        }
        return builder
    }

    private fun removeAuthHeaders(builder: Request.Builder) {
        builder.removeHeader(Header.AUTH)
        builder.removeHeader(Header.NO_AUTH)
    }

    private fun hasNoAuthHeader(request: Request): Boolean {
        return request.headers().values(Header.AT).contains(Header.VALUE_NO_AUTH)
    }

    private fun hasAuthHeader(request: Request): Boolean {
        return request.headers().values(Header.AT).contains(Header.VALUE_AUTH)
    }
}
