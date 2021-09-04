package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.home.profile.ProfileContract
import com.codetron.foodmarketmvp.ui.home.profile.ProfileFragment
import com.codetron.foodmarketmvp.ui.home.profile.ProfilePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(FragmentComponent::class)
interface ProfileModule {

    companion object {
        @Provides
        fun provideProfileFragment(fragment: Fragment): ProfileFragment {
            return fragment as ProfileFragment
        }
    }

    @Binds
    fun bindView(fragment: ProfileFragment): ProfileContract.View

    @Binds
    fun bindPresenter(presenter: ProfilePresenter): ProfileContract.Presenter

}