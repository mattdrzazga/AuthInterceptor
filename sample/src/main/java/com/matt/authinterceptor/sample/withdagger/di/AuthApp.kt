package com.matt.authinterceptor.sample.withdagger.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class AuthApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}
