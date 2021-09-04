package com.codetron.foodmarketmvp.ui.home.profile

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.user.User

interface ProfileContract {

    interface View : BaseViewContract {
        fun initState()
        fun onGetUserDataSuccess(user:User)
        fun onGetUserDataFailed(message:String)
    }

    interface Presenter : BasePresenterContract

}