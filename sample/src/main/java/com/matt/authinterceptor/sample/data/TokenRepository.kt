package com.matt.authinterceptor.sample.data

import android.content.SharedPreferences
import com.matt.authinterceptor.TokenStore

class TokenRepository(private val preferences: SharedPreferences) : TokenStore {

    override fun getToken(): String {
        val token = preferences.getString(KEY_TOKEN, "")
        return "Bearer $token"
    }

    override fun getTokenHeader() = "Authorization"

    companion object {
        private const val KEY_TOKEN = "token"
    }
}
