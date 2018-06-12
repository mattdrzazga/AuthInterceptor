package com.matt.authinterceptor.sample.api

import com.google.gson.JsonObject
import com.matt.authinterceptor.HeaderWriter
import com.matt.authinterceptor.sample.api.data.Credentials
import com.matt.authinterceptor.sample.api.data.Profile
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {

    @GET("profile")
    fun userProfile(): Call<Profile>

    @POST("profile")
    @Headers(HeaderWriter.NO_AUTH_HEADER)
    fun signUp(@Body credentials: Credentials): Call<Profile>

    @Multipart
    @POST("attachment")
    @Headers(HeaderWriter.NO_AUTH_HEADER)
    fun uploadAttachmentAsUser(@Part file: MultipartBody.Part): Call<JsonObject>

    @Multipart
    @POST("attachment")
    fun uploadAttachmentAsGuest(@Part file: MultipartBody.Part): Call<JsonObject>
}
