package com.codetron.foodmarketmvp.ui.payment

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.food.FoodCheckout
import com.codetron.foodmarketmvp.model.domain.user.User

interface PaymentContract {

    interface View : BaseViewContract {
        fun initState()
        fun onGetFoodDataSuccess(foodCheckout: FoodCheckout)
        fun onGetFoodDataFailed(message: String)
        fun onGetUserDataSuccess(user: User)
        fun onGetUserDataFailed(message: String)
    }

    interface Presenter : BasePresenterContract {
        fun submitCheckout()
    }

}