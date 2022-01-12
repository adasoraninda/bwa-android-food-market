package com.codetron.foodmarketmvp.di.component

import android.content.Context
import com.codetron.foodmarketmvp.di.module.common.FormValidationModule
import com.codetron.foodmarketmvp.di.module.local.DataStoreModule
import com.codetron.foodmarketmvp.di.module.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
@Component(modules = [NetworkModule::class, DataStoreModule::class, FormValidationModule::class])
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    fun newFragmentComponentBuilder(): FragmentComponent.Builder

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}