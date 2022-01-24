package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.di.scope.FragmentScope
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.home.profile.ProfileContract
import com.codetron.foodmarketmvp.ui.home.profile.ProfileFragment
import com.codetron.foodmarketmvp.ui.home.profile.ProfilePresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class ProfileModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): ProfileContract.View {
        return fragment as ProfileFragment
    }

    @Provides
    @FragmentScope
    fun providePresenter(
        view: ProfileContract.View,
        dataStore: UserDataStore,
        service: FoodMarketApi
    ): ProfileContract.Presenter {
        return ProfilePresenter(
            view, dataStore, service
        )
    }

}