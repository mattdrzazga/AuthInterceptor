package com.matt.authinterceptor.sample.api

import com.google.gson.JsonObject
import com.matt.authinterceptor.AUTH
import com.matt.authinterceptor.NO_AUTH
import com.matt.authinterceptor.sample.api.data.Credentials
import com.matt.authinterceptor.sample.api.data.Profile
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface RestApiService {

    @GET("profile")
    fun userProfile(): Call<Profile>

    @POST("profile")
    @Headers(NO_AUTH)
    fun signUp(@Body credentials: Credentials): Call<Profile>

    @GET("articles")
    fun getArticlesDefault(): Call<JsonObject>

    @GET("articles")
    @Headers(AUTH)
    fun getArticlesAuth(): Call<JsonObject>

    @GET("articles")
    @Headers(NO_AUTH)
    fun getArticlesNoAuth(): Call<JsonObject>

    @Multipart
    @POST("attachment")
    @Headers(NO_AUTH)
    fun uploadAttachmentAsUser(@Part file: MultipartBody.Part): Call<JsonObject>

    @Multipart
    @POST("attachment")
    fun uploadAttachmentAsGuest(@Part file: MultipartBody.Part): Call<JsonObject>
}
