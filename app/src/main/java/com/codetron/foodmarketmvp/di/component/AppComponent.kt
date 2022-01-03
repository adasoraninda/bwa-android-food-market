package com.codetron.foodmarketmvp.di.component

import android.content.Context
import com.codetron.foodmarketmvp.di.module.local.DataStoreModule
import com.codetron.foodmarketmvp.di.module.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
@Component(modules = [NetworkModule::class, DataStoreModule::class])
interface AppComponent {

    fun newUiComponentBuilder(): UiComponent.Builder

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}