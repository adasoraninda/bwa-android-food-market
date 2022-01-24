package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.di.scope.FragmentScope
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesContract
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class FoodCategoriesModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): FoodCategoriesContract.View {
        return fragment as FoodCategoriesFragment
    }

}