package com.codetron.foodmarketmvp.ui.splash

import com.codetron.foodmarketmvp.base.BasePresenterContract

interface SplashContract {

    interface View {
        fun navigate(isLoggedIn: Boolean)
    }

    interface Presenter : BasePresenterContract

}