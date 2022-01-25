package com.codetron.foodmarketmvp.util

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.FoodMarketApplication
import com.codetron.foodmarketmvp.di.component.ActivityComponent
import com.codetron.foodmarketmvp.di.component.AppComponent
import com.codetron.foodmarketmvp.di.component.FragmentComponent
import com.codetron.foodmarketmvp.di.module.ui.activity.ActivityModule
import com.codetron.foodmarketmvp.di.module.ui.fragment.FragmentModule
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun Application.appComponent(): AppComponent {
    return (this as FoodMarketApplication).appComponent
}

@ExperimentalCoroutinesApi
fun AppComponent.activityComponent(activity: AppCompatActivity): ActivityComponent {
    return this.newActivityComponentBuilder()
        .activityModule(ActivityModule(activity = activity))
        .build()
}

@ExperimentalCoroutinesApi
fun ActivityComponent.fragmentComponent(fragment: Fragment): FragmentComponent {
    return this.newFragmentComponentBuilder()
        .fragmentModule(FragmentModule(fragment = fragment))
        .build()
}