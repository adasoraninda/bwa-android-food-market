package com.codetron.foodmarketmvp.ui.detail

import com.codetron.foodmarketmvp.model.domain.food.Food
import com.codetron.foodmarketmvp.model.domain.food.FoodCheckout
import com.codetron.foodmarketmvp.model.response.food.toDetailDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailFoodPresenter @AssistedInject constructor(
    private val view: DetailFoodContract.View,
    private val serviceApi: FoodMarketApi,
    @Assisted private val foodId: Int
) : DetailFoodContract.Presenter {

    @AssistedFactory
    interface Factory {
        fun create(foodId: Int): DetailFoodPresenter
    }

    private val compositeDisposable by lazy { CompositeDisposable() }
    private var totalItem = 1
    private var food: Food? = null

    override fun subscribe() {
        view.initState()
        view.showLoading()

        val disposable = serviceApi.getFoodById(foodId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code == 200 && body != null) {
                    view.onGetDataSuccess(body.toDetailDomain())
                    food = body.toDetailDomain()
                } else {
                    view.onGetDataFailed(response.meta?.message.toString())
                }

                view.dismissLoading()
            }, { error ->
                val message = handleException(error)

                view.dismissLoading()
                view.onGetDataFailed(message)
            })

        compositeDisposable.add(disposable)
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    override fun buttonMinTotalPressed() {
        totalItem -= 1
        if (totalItem == 1) {
            view.toggleMinButton(false)
        }
        view.setTotalProduct(totalItem)
        food?.let { food -> view.updatePrice(totalItem * food.price) }
    }

    override fun buttonAddTotalPressed() {
        view.toggleMinButton(true)
        view.setTotalProduct(++totalItem)
        food?.let { food -> view.updatePrice(totalItem * food.price) }
    }

    override fun onCheckOutClicked() {
        if (food == null) return

        view.submitCheckout(
            FoodCheckout(
                food!!,
                totalItem
            )
        )
    }
}