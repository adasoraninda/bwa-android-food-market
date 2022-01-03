package com.codetron.foodmarketmvp

import android.app.Application
import com.codetron.foodmarketmvp.di.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FoodMarketApplication : Application() {

    @ExperimentalCoroutinesApi
    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

}