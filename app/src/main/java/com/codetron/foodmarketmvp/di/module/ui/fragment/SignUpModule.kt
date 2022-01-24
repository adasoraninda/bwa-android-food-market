package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.common.SignUpValidation
import com.codetron.foodmarketmvp.di.scope.FragmentScope
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpContract
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpFragment
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SignUpModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): SignUpContract.View {
        return fragment as SignUpFragment
    }

    @Provides
    @FragmentScope
    fun providePresenter(
        view: SignUpContract.View,
        @SignUpValidation validation: FormValidation
    ): SignUpContract.Presenter {
        return SignUpPresenter(view, validation)
    }
}