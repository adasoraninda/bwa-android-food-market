package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.di.scope.FragmentScope
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuContract
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class ProfileMenuModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): ProfileMenuContract.View {
        return fragment as ProfileMenuFragment
    }


}