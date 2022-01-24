package com.codetron.foodmarketmvp.di.module.ui.fragment

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.common.SignInValidation
import com.codetron.foodmarketmvp.di.scope.FragmentScope
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.codetron.foodmarketmvp.ui.auth.signin.SignInContract
import com.codetron.foodmarketmvp.ui.auth.signin.SignInFragment
import com.codetron.foodmarketmvp.ui.auth.signin.SignInPresenter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
class SignInModule {

    @Provides
    @FragmentScope
    fun provideView(fragment: Fragment): SignInContract.View {
        return fragment as SignInFragment
    }

    @Provides
    @FragmentScope
    fun providePresenter(
        view: SignInContract.View,
        service: FoodMarketApi,
        dataStore: UserDataStore,
        @SignInValidation validation: FormValidation
    ): SignInContract.Presenter {
        return SignInPresenter(view, dataStore, service, validation)
    }
}