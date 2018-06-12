package com.matt.authinterceptor.sample.withdagger.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.matt.authinterceptor.AuthInterceptor
import com.matt.authinterceptor.HeaderWriter
import com.matt.authinterceptor.TokenStore
import com.matt.authinterceptor.sample.api.RestApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Module that provides Retrofit instance.
 */
@Module
class NetworkModule {

    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun provideGson(): Gson {
            return GsonBuilder().create()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideOkHttpClient(headersInterceptor: AuthInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor(headersInterceptor)
                    .build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("www.example.com/")
                    .client(okHttpClient)
                    .build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideHeaderWriter(repository: TokenStore): HeaderWriter {
            return HeaderWriter(repository)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideAuthInterceptor(writer: HeaderWriter): AuthInterceptor {
            return AuthInterceptor(writer)
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideRestApiService(retrofit: Retrofit): RestApiService {
            return retrofit.create<RestApiService>(RestApiService::class.java)
        }
    }
}
