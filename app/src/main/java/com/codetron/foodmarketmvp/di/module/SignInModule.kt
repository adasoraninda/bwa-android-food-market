package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.ui.auth.signin.SignInContract
import com.codetron.foodmarketmvp.ui.auth.signin.SignInFragment
import com.codetron.foodmarketmvp.ui.auth.signin.SignInPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Module(includes = [FormValidationModule::class, DataStoreModule::class])
@InstallIn(FragmentComponent::class)
class SignInModule {

    @Provides
    fun provideSignInPresenter(
        view: SignInContract.View,
        userDataStore: UserDataStore,
        @SignInValidation formValidation: FormValidation
    ): SignInPresenter {
        return SignInPresenter(view, userDataStore, formValidation)
    }

    @Provides
    fun provideFragment(fragment: Fragment): SignInFragment {
        return fragment as SignInFragment
    }

    @Provides
    fun provideView(fragment: SignInFragment): SignInContract.View {
        return fragment
    }

    @Provides
    fun providePresenter(presenter: SignInPresenter): SignInContract.Presenter {
        return presenter
    }

}