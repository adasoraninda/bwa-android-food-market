package com.codetron.foodmarketmvp.di.component

import com.codetron.foodmarketmvp.di.module.common.FormValidationModule
import com.codetron.foodmarketmvp.di.module.ui.*
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpFragment
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
        FormValidationModule::class,
    ]
)
interface UiComponent {

    @Subcomponent.Builder
    interface Builder {
        fun foodCategoriesModule(module: FoodCategoriesModule): Builder
        fun dashboardModule(module: DashboardModule): Builder
        fun signInModule(module: SignInModule): Builder
        fun signUpAddressModule(module: SignUpAddressModule): Builder
        fun signUpModule(module: SignUpModule): Builder
        fun splashModule(module: SplashModule): Builder
        fun build(): UiComponent
    }

    fun inject(fragment: SignUpFragment)
    fun inject(activity: SplashActivity)
}