package com.codetron.foodmarketmvp.di.module

import android.app.Activity
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.ui.splash.SplashActivity
import com.codetron.foodmarketmvp.ui.splash.SplashContract
import com.codetron.foodmarketmvp.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Module(includes = [DataStoreModule::class])
@InstallIn(ActivityComponent::class)
class SplashModule {

    @Provides
    fun provideSplashPresenter(
        view: SplashContract.View,
        dataStore: UserDataStore
    ): SplashPresenter {
        return SplashPresenter(view, dataStore)
    }

    @Provides
    fun provideActivity(activity: Activity): SplashActivity {
        return activity as SplashActivity
    }

    @Provides
    fun provideView(activity: SplashActivity): SplashContract.View {
        return activity
    }

    @Provides
    fun providePresenter(presenter: SplashPresenter): SplashContract.Presenter {
        return presenter
    }
}