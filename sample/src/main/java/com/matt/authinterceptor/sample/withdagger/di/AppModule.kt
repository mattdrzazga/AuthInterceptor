package com.matt.authinterceptor.sample.withdagger.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.matt.authinterceptor.TokenStore
import com.matt.authinterceptor.sample.data.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module that provides the application context and other globally managed dependencies.
 */
@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(app: AuthApp): Context

    @Binds
    abstract fun bindTokenRepository(repository: TokenRepository): TokenStore

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }
}
