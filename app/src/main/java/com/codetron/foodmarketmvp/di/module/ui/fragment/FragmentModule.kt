package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.di.module.ui.activity.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(
    private val fragment: Fragment
) {

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment {
        return fragment
    }

}