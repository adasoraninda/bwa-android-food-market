package com.codetron.foodmarketmvp.di.module

import android.app.Activity
import com.codetron.foodmarketmvp.ui.splash.SplashActivity
import com.codetron.foodmarketmvp.ui.splash.SplashContract
import com.codetron.foodmarketmvp.ui.splash.SplashPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Module
@ExperimentalCoroutinesApi
@InstallIn(ActivityComponent::class)
interface SplashModule {

    companion object {
        @Provides
        fun provideActivity(activity: Activity): SplashActivity {
            return activity as SplashActivity
        }
    }

    @Binds
    fun bindView(activity: SplashActivity): SplashContract.View

    @Binds
    fun bindPresenter(presenter: SplashPresenter): SplashContract.Presenter
}