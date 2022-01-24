package com.codetron.foodmarketmvp.di.module.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.di.scope.ActivityScope
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.ui.splash.SplashActivity
import com.codetron.foodmarketmvp.ui.splash.SplashContract
import com.codetron.foodmarketmvp.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SplashModule {

    @Provides
    @ActivityScope
    fun provideView(activity: AppCompatActivity): SplashContract.View {
        return activity as SplashActivity
    }

    @Provides
    @ActivityScope
    fun providePresenter(
        view: SplashContract.View,
        dataStore: UserDataStore
    ): SplashContract.Presenter {
        return SplashPresenter(view, dataStore)
    }

}