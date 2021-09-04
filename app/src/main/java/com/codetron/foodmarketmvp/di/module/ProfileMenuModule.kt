package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuContract
import com.codetron.foodmarketmvp.ui.home.profile.menu.ProfileMenuFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface ProfileMenuModule {

    companion object {
        @Provides
        fun provideProfileMenuFragment(fragment: Fragment): ProfileMenuFragment {
            return fragment as ProfileMenuFragment
        }
    }

    @Binds
    fun bindView(fragment: ProfileMenuFragment): ProfileMenuContract.View

}