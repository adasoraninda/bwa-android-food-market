package com.codetron.foodmarketmvp.di.component

import com.codetron.foodmarketmvp.di.module.common.FormValidationModule
import com.codetron.foodmarketmvp.di.module.ui.activity.ActivityModule
import com.codetron.foodmarketmvp.di.module.ui.activity.SplashModule
import com.codetron.foodmarketmvp.di.scope.ActivityScope
import com.codetron.foodmarketmvp.ui.splash.SplashActivity
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ActivityScope
@ExperimentalCoroutinesApi
@Subcomponent(
    modules = [
        ActivityModule::class,
        SplashModule::class,
        FormValidationModule::class
    ]
)
interface ActivityComponent {

    fun newFragmentComponentBuilder(): FragmentComponent.Builder

    fun inject(activity: SplashActivity)

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): Builder
        fun build(): ActivityComponent
    }

}