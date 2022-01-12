package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardContract
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardFragment
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class DashboardModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): DashboardContract.View {
        return fragment as DashboardFragment
    }

    @Provides
    @FragmentScope
    fun providePresenter(
        view: DashboardContract.View,
        service: FoodMarketApi,
        dataStore: UserDataStore
    ): DashboardContract.Presenter {
        return DashboardPresenter(view, service, dataStore)
    }

}