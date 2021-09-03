package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesContract
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface FoodCategoriesModule {

    companion object {
        @Provides
        fun provideFoodCategoriesFragment(fragment: Fragment): FoodCategoriesFragment {
            return fragment as FoodCategoriesFragment
        }
    }

    @Binds
    fun bindView(fragment: FoodCategoriesFragment): FoodCategoriesContract.View

}