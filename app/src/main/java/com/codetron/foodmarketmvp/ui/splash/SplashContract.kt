package com.codetron.foodmarketmvp.ui.splash

interface SplashContract {

    interface View {
        fun navigate(key: String)
    }

    interface Presenter {
        fun subscribe()
        fun unSubscribe()
    }

}