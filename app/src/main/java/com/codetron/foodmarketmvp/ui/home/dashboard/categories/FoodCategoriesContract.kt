package com.codetron.foodmarketmvp.ui.home.dashboard.categories

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.food.FoodItem

interface FoodCategoriesContract {

    interface View : BaseViewContract {
        fun onGetFoodCategoriesSuccess(foods:List<FoodItem>)
        fun onGetFoodCategoriesFailed(message:String)
    }

    interface Presenter : BasePresenterContract

}