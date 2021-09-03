package com.codetron.foodmarketmvp.di.module

import android.app.Activity
import com.codetron.foodmarketmvp.ui.detail.DetailFoodActivity
import com.codetron.foodmarketmvp.ui.detail.DetailFoodContract
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface DetailFoodModule {

    companion object {
        @Provides
        fun provideDetailFoodActivity(activity: Activity): DetailFoodActivity {
            return activity as DetailFoodActivity
        }
    }

    @Binds
    fun bindView(activity: DetailFoodActivity): DetailFoodContract.View

}