package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressContract
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(FragmentComponent::class)
interface SignUpAddressModule {

    companion object {

        @Provides
        fun provideFragment(fragment: Fragment): SignUpAddressFragment {
            return fragment as SignUpAddressFragment
        }
    }

    @Binds
    fun bindView(fragment: SignUpAddressFragment): SignUpAddressContract.View

}