package com.codetron.foodmarketmvp.ui.home.dashboard.categories

import com.codetron.foodmarketmvp.model.response.food.FoodResponse
import com.codetron.foodmarketmvp.model.response.food.toItemDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FoodCategoriesPresenter (
    private val view: FoodCategoriesContract.View,
    private val apiService: FoodMarketApi,
) : FoodCategoriesContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        view.showLoading()
        val disposable = apiService.getAllFood(types = "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data?.data

                if (code == 200 && !body.isNullOrEmpty()) {
                    view.onGetFoodCategoriesSuccess(body.map(FoodResponse::toItemDomain))
                } else {
                    view.onGetFoodCategoriesFailed(response.meta?.message.toString())
                }

                view.dismissLoading()
            }, { error ->
                val message = handleException(error)

                view.onGetFoodCategoriesFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

}