package com.matt.authinterceptor.sample.simple

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.JsonArray
import com.matt.authinterceptor.AuthInterceptor
import com.matt.authinterceptor.HeaderWriter
import com.matt.authinterceptor.sample.R
import com.matt.authinterceptor.sample.api.RestApiService
import com.matt.authinterceptor.sample.data.TokenRepository
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        val repository = TokenRepository(PreferenceManager.getDefaultSharedPreferences(this))
        val authInterceptor = AuthInterceptor.create(repository)

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://www.example.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(RestApiService::class.java)

        val callback: Callback<JsonArray> = object : Callback<JsonArray> {
            override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                Log.v("SimpleActivity", "onFailure: " + t.toString())
            }

            override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                Log.v("SimpleActivity", "onResponse body: " + response?.body().toString())
            }
        }
        api.getUsersDefault().enqueue(callback)
        api.getUsersAuth().enqueue(callback)
        api.getUsersNoAuth().enqueue(callback)
    }
}
