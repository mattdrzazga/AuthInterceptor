package com.matt.authinterceptor

import okhttp3.Request

class HeaderWriter(private val repository: TokenStore) {

    fun modifyRequest(request: Request): Request.Builder {
        val token = repository.getToken()
        val header = repository.getTokenHeader()
        val builder = request.newBuilder()

        @Suppress("LiftReturnOrAssignment")
        if (hasNoAuthHeader(request)) {
            return builder.removeHeader(NO_AUTH)
        } else {
            return builder.addHeader(header, token)
        }
    }

    private fun hasNoAuthHeader(request: Request): Boolean {
        return request.headers().values(AT).isNotEmpty()
    }

    private fun hasAtHeaders(request: Request): Boolean {
        return request.headers().values(AT).isNotEmpty()
    }
}
