package com.codetron.foodmarketmvp.di.module.ui

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.common.SignInValidation
import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.auth.signin.SignInContract
import com.codetron.foodmarketmvp.ui.auth.signin.SignInPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SignInModule(
    private val view: SignInContract.View
) {

    @UiScope
    @Provides
    fun providePresenter(
        dataStore: UserDataStore,
        service: FoodMarketApi,
        @SignInValidation validation: FormValidation
    ): SignInContract.Presenter {
        return SignInPresenter(
            view,
            dataStore,
            service,
            validation
        )
    }

}