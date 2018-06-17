package com.matt.authinterceptor.sample.api

import com.google.gson.JsonArray
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
    
    @Multipart
    @POST("attachment")
    @Headers(NO_AUTH)
    fun uploadAttachmentAsUser(@Part file: MultipartBody.Part): Call<JsonObject>

    @Multipart
    @POST("attachment")
    fun uploadAttachmentAsGuest(@Part file: MultipartBody.Part): Call<JsonObject>

    @GET("users")
    fun getUsersDefault(): Call<JsonArray>

    @GET("users")
    @Headers(AUTH)
    fun getUsersAuth(): Call<JsonArray>

    @GET("users")
    @Headers(NO_AUTH)
    fun getUsersNoAuth(): Call<JsonArray>
}
