package com.codetron.foodmarketmvp.di.module.ui

import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.ui.splash.SplashContract
import com.codetron.foodmarketmvp.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SplashModule(
    private val view: SplashContract.View
) {

    @UiScope
    @Provides
    fun providePresenter(dataStore: UserDataStore): SplashContract.Presenter {
        return SplashPresenter(view, dataStore)
    }

}