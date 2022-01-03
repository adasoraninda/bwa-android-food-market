package com.codetron.foodmarketmvp.ui.payment

import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.domain.food.FoodCheckout
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PaymentPresenter (
    private val view: PaymentContract.View,
    private val dataStore: UserDataStore,
    private val serviceApi: FoodMarketApi,
) : PaymentContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        view.initState()
        getToken()
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    override fun submitCheckout() {

    }

    private fun getFoodData(foodCheckout: FoodCheckout?) {
        if (foodCheckout == null) {
            view.onGetFoodDataFailed("Food data is empty")
            return
        }

        view.onGetFoodDataSuccess(foodCheckout)
    }

    private fun getUserData(token: String) {
        view.showLoading()

        val disposable = serviceApi.getUser(token)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code == 200 && body != null) {
                    view.onGetUserDataSuccess(body.toDomain())
                } else {
                    view.onGetUserDataFailed("Failed to get user data")
                }

                view.dismissLoading()
            }, { error ->
                val message = handleException(error)

                view.onGetUserDataFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    private fun getToken() {
        view.showLoading()

        val disposable = dataStore.getToken()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ token ->
                if (!token.isNullOrEmpty()) {
                    getUserData(token)
                } else {
                    view.onGetUserDataFailed("Token is empty")
                    view.dismissLoading()
                }
            }, { error ->
                val message = handleException(error)
                view.onGetUserDataFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

}