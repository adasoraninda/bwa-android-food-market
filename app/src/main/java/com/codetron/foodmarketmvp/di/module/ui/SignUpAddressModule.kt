package com.codetron.foodmarketmvp.di.module.ui

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.common.SignUpAddressValidation
import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.domain.user.UserRegister
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressContract
import com.codetron.foodmarketmvp.ui.auth.signup.address.SignUpAddressPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SignUpAddressModule(
    private val view: SignUpAddressContract.View,
    private val userRegister: UserRegister?,
) {

    @UiScope
    @Provides
    fun providePresenter(
        dataStore: UserDataStore,
        service: FoodMarketApi,
        @SignUpAddressValidation validation: FormValidation
    ): SignUpAddressContract.Presenter {
        return SignUpAddressPresenter(
            view,
            dataStore,
            service,
            validation,
            userRegister
        )
    }

}