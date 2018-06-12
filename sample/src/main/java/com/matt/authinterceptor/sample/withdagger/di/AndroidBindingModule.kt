package com.matt.authinterceptor.sample.withdagger.di

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Uses dagger-android to inject dependencies into activities and fragments.
 */
@Module
interface AndroidBindingModule {
    @ContributesAndroidInjector
    fun activityWithDagger(): ActivityWithDagger
}