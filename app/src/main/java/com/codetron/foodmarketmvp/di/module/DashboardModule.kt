package com.codetron.foodmarketmvp.di.module

import androidx.fragment.app.Fragment
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardContract
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardFragment
import com.codetron.foodmarketmvp.ui.home.dashboard.DashboardPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(FragmentComponent::class)
interface DashboardModule {

    companion object {

        @Provides
        fun provideDashboardFragment(fragment: Fragment): DashboardFragment {
            return fragment as DashboardFragment
        }

    }

    @Binds
    fun bindView(fragment: DashboardFragment): DashboardContract.View

    @Binds
    fun bindPresenter(presenter: DashboardPresenter): DashboardContract.Presenter

}