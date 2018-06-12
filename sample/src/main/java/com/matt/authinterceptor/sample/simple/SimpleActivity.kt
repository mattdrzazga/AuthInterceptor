package com.matt.authinterceptor.sample.simple

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.matt.authinterceptor.AuthInterceptor
import com.matt.authinterceptor.HeaderWriter
import com.matt.authinterceptor.sample.R
import com.matt.authinterceptor.sample.data.TokenRepository
import com.matt.authinterceptor.sample.api.RestApiService
import com.matt.authinterceptor.sample.api.data.Credentials
import com.matt.authinterceptor.sample.api.data.Profile
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
        val headerWriter = HeaderWriter(repository)
        val authInterceptor = AuthInterceptor(headerWriter)

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("www.example.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(RestApiService::class.java)


        api.userProfile().enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>?, response: Response<Profile>?) {

            }

            override fun onFailure(call: Call<Profile>?, t: Throwable?) {

            }
        })

        val credentials = Credentials("thisIsLogin", "thisIsPassword")
        api.signUp(credentials).enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>?, response: Response<Profile>?) {

            }

            override fun onFailure(call: Call<Profile>?, t: Throwable?) {

            }

        })
    }
}
