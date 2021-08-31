package com.codetron.foodmarketmvp.ui.splash

import com.codetron.foodmarketmvp.base.BasePresenterContract

interface SplashContract {

    interface View {
        fun navigate(key: String)
    }

    interface Presenter : BasePresenterContract

}