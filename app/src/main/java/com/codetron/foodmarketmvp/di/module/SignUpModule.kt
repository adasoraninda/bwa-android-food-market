package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpContract
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpFragment
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface SignUpModule {

    companion object {
        @Provides
        fun provideFragment(fragment: Fragment): SignUpFragment {
            return fragment as SignUpFragment
        }
    }

    @Binds
    fun bindView(fragment: SignUpFragment): SignUpContract.View

    @Binds
    fun bindPresenter(presenter: SignUpPresenter): SignUpContract.Presenter

}