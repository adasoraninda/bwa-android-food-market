package com.codetron.foodmarketmvp.di.component

import com.codetron.foodmarketmvp.di.module.ui.fragment.*
import com.codetron.foodmarketmvp.ui.auth.signin.SignInFragment
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpFragment
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressFragment
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardFragment
import com.codetron.foodmarketmvp.ui.home.dashboard.categories.FoodCategoriesFragment
import com.codetron.foodmarketmvp.ui.home.profile.ProfileFragment
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@FragmentScope
@Subcomponent(
    modules = [
        FragmentModule::class,
        DashboardModule::class,
        FoodCategoriesModule::class,
        SignInModule::class,
        SignUpModule::class,
        SignUpAddressModule::class,
        ProfileModule::class,
        ProfileMenuModule::class,
    ]
)
@ExperimentalCoroutinesApi
interface FragmentComponent {

    fun inject(fragment: SignInFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: SignUpAddressFragment)
    fun inject(fragment: DashboardFragment)
    fun inject(fragment: FoodCategoriesFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: ProfileMenuFragment)

    @Subcomponent.Builder
    interface Builder {
        fun fragmentModule(fragmentModule: FragmentModule): Builder
        fun build(): FragmentComponent
    }
}