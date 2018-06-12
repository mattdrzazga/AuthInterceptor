package com.matt.authinterceptor.sample.api

import com.matt.authinterceptor.HeaderWriter
import com.matt.authinterceptor.sample.api.data.Credentials
import com.matt.authinterceptor.sample.api.data.Profile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApiService {

    @GET("profile")
    fun userProfile(): Call<Profile>

    @POST("profile")
    @Headers(HeaderWriter.NO_AUTH_HEADER)
    fun signUp(@Body credentials: Credentials): Call<Profile>
}
