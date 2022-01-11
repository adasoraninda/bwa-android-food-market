package com.codetron.foodmarketmvp.di.module.ui

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.common.SignUpValidation
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpContract
import com.codetron.foodmarketmvp.ui.auth.signup.account.SignUpPresenter
import dagger.Module
import dagger.Provides

@Module
class SignUpModule(
    private val view: SignUpContract.View
) {

    @UiScope
    @Provides
    fun providePresenter(
        @SignUpValidation validation: FormValidation
    ): SignUpContract.Presenter {
        return SignUpPresenter(view, validation)
    }
}