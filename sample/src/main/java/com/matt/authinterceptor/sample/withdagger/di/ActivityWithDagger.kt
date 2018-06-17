package com.matt.authinterceptor.sample.withdagger.di

import android.os.Bundle
import android.util.Log
import com.google.gson.JsonArray
import com.matt.authinterceptor.sample.R
import com.matt.authinterceptor.sample.api.RestApiService
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

        val callback: Callback<JsonArray> = object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                Log.v("AWD", "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                Log.v("AWD", "onResponse body: " + response?.body().toString())
            }
        }
        api.getUsersDefault().enqueue(callback)
        api.getUsersAuth().enqueue(callback)
        api.getUsersNoAuth().enqueue(callback)
    }
}
