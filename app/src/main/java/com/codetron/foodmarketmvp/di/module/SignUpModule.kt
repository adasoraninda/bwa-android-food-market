package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpContract
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpFragment
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module(includes = [FormValidationModule::class])
@InstallIn(FragmentComponent::class)
class SignUpModule {

    @Provides
    fun provideSignUpPresenter(
        view: SignUpContract.View,
        @SignUpValidation formValidation: FormValidation
    ): SignUpPresenter {
        return SignUpPresenter(view, formValidation)
    }

    @Provides
    fun provideFragment(fragment: Fragment): SignUpFragment {
        return fragment as SignUpFragment
    }

    @Provides
    fun provideView(fragment: SignUpFragment): SignUpContract.View {
        return fragment
    }

    @Provides
    fun providePresenter(presenter: SignUpPresenter): SignUpContract.Presenter {
        return presenter
    }

}