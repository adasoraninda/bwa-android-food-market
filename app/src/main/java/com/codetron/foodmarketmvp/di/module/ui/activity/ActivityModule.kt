package com.codetron.foodmarketmvp.di.module.ui.activity

import androidx.appcompat.app.AppCompatActivity
import com.codetron.foodmarketmvp.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {

    @ActivityScope
    @Provides
    fun provideActivity():AppCompatActivity{
        return activity
    }

}