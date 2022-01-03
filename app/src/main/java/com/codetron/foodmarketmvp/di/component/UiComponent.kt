package com.codetron.foodmarketmvp.di.component

import com.codetron.foodmarketmvp.di.module.ui.*
import com.codetron.foodmarketmvp.ui.splash.SplashActivity
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@UiScope
@ExperimentalCoroutinesApi
@Subcomponent(
    modules = [
        SplashModule::class,
        SignInModule::class,
        SignUpModule::class,
        SignUpAddressModule::class,
        DashboardModule::class,
        ProfileModule::class,
        ProfileMenuModule::class,
        PaymentModule::class,
        DetailFoodModule::class,
        FoodCategoriesModule::class,
    ]
)
interface UiComponent {

    @Subcomponent.Builder
    interface Builder {
        fun splashModule(module: SplashModule): Builder
        fun build(): UiComponent
    }

    fun inject(activity: SplashActivity)
}