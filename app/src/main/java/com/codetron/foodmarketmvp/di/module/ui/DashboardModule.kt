package com.codetron.foodmarketmvp.di.module.ui

import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardContract
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class DashboardModule(
    private val view: DashboardContract.View
) {

    @UiScope
    @Provides
    fun providePresenter(
        service: FoodMarketApi,
        dataStore: UserDataStore
    ): DashboardContract.Presenter {
        return DashboardPresenter(
            view,
            service,
            dataStore
        )
    }

}