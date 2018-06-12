package com.matt.authinterceptor.sample.withdagger.di

import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import com.matt.authinterceptor.sample.R
import com.matt.authinterceptor.sample.api.RestApiService
import com.matt.authinterceptor.sample.api.data.Profile
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ActivityWithDagger : DaggerAppCompatActivity() {
    @Inject
    lateinit var api: RestApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_dagger)

        api.userProfile().enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>?, t: Throwable?) {
                Log.v("AWD", "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<Profile>?, response: Response<Profile>?) {
                Log.v("AWD", "onResponse: " + response?.body().toString())
            }
        })

        val callback = object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.v("AWD", "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                Log.v("AWD", "onResponse: " + response?.body().toString())
            }
        }

        api.getArticlesDefault().enqueue(callback)
        api.getArticlesAuth().enqueue(callback)
        api.getArticlesNoAuth().enqueue(callback)
    }
}
