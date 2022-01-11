package com.codetron.foodmarketmvp.di.module.ui

import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesContract
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class FoodCategoriesModule(
    private val view: FoodCategoriesContract.View,
    private val foodTypes: String?
) {

    @UiScope
    @Provides
    fun providePresenter(
        service: FoodMarketApi
    ): FoodCategoriesContract.Presenter {
        return FoodCategoriesPresenter(
            view,
            service,
            foodTypes
        )
    }

}