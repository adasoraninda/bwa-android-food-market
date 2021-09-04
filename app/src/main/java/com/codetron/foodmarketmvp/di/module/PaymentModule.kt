package com.codetron.foodmarketmvp.di.module

import android.app.Activity
import com.codetron.foodmarketmvp.ui.payment.PaymentActivity
import com.codetron.foodmarketmvp.ui.payment.PaymentContract
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(ActivityComponent::class)
interface PaymentModule {

    companion object {
        @Provides
        fun providePaymentActivity(activity: Activity): PaymentActivity {
            return activity as PaymentActivity
        }
    }

    @Binds
    fun bindView(activity: PaymentActivity): PaymentContract.View

}