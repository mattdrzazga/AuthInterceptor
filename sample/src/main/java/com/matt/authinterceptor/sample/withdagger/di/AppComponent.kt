package com.matt.authinterceptor.sample.withdagger.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    AndroidBindingModule::class,
    NetworkModule::class])
@Singleton
interface AppComponent : AndroidInjector<AuthApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AuthApp>()
}

