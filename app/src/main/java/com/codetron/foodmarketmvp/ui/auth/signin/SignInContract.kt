package com.codetron.foodmarketmvp.ui.auth.signin

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.user.User

interface SignInContract {

    interface View : BaseViewContract {
        fun onLoginSuccess(user: User)
        fun onLoginFailed(message: String)
        fun inputFormMessage(messageMap: Map<String, String?>)
    }

    interface Presenter : BasePresenterContract {
        fun submitLogin(email: String?, password: String?)
    }

}