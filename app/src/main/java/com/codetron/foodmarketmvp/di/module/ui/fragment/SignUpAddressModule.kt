package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.di.scope.FragmentScope
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressContract
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SignUpAddressModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): SignUpAddressContract.View {
        return fragment as SignUpAddressFragment
    }

}