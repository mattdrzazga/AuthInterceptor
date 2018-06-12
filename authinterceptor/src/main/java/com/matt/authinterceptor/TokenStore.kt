package com.matt.authinterceptor

interface TokenStore {
    fun getToken(): String

    fun getTokenHeader(): String
}
