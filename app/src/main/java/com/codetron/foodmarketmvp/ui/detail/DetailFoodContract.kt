package com.codetron.foodmarketmvp.ui.detail

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.food.Food

interface DetailFoodContract {

    interface View : BaseViewContract {
        fun initState()
        fun onGetDataSuccess(food: Food)
        fun onGetDataFailed(message: String)
        fun setTotalProduct(total: Int)
        fun toggleMinButton(isEnabled: Boolean)
        fun submitCheckout()
        fun updatePrice(price: Int)
    }

    interface Presenter : BasePresenterContract {
        fun buttonMinTotalPressed()
        fun buttonAddTotalPressed()
    }

}