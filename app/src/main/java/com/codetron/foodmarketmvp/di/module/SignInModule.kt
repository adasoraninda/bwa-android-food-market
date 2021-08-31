package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.auth.signin.SignInContract
import com.codetron.foodmarketmvp.ui.auth.signin.SignInFragment
import com.codetron.foodmarketmvp.ui.auth.signin.SignInPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(FragmentComponent::class)
interface SignInModule {

    companion object {
        @Provides
        fun provideFragment(fragment: Fragment): SignInFragment {
            return fragment as SignInFragment
        }
    }

    @Binds
    fun bindView(fragment: SignInFragment): SignInContract.View

    @Binds
    fun bindPresenter(presenter: SignInPresenter): SignInContract.Presenter

}