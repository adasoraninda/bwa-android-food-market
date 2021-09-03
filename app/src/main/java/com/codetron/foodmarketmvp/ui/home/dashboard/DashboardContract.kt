package com.codetron.foodmarketmvp.ui.home.dashboard

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.food.FoodItem
import com.codetron.foodmarketmvp.model.domain.user.User

interface DashboardContract {

    interface View : BaseViewContract {
        fun onGetFoodSuccess(foods: List<FoodItem>)
        fun onGetUserSuccess(user: User)
        fun onGetDataFailed(message: String)
    }

    interface Presenter : BasePresenterContract {
        fun getAllFood()
        fun getUser(token:String)
    }

}