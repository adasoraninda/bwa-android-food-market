package com.codetron.foodmarketmvp.ui.home.dashboard

import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.response.food.FoodResponse
import com.codetron.foodmarketmvp.model.response.food.toItemDomain
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DashboardPresenter(
    private val view: DashboardContract.View,
    private val apiService: FoodMarketApi,
    private val dataStore: UserDataStore
) : DashboardContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        getUserData()
        getAllFood()
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    override fun getAllFood() {
        apiService.getAllFood()
            .doOnSubscribe { view.showLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data?.data

                if (code == 200 && !body.isNullOrEmpty()) {
                    view.onGetFoodSuccess(body.map(FoodResponse::toItemDomain))
                }

                view.dismissLoading()
            }, { error ->
                val message = handleException(error)

                view.onGetDataFailed(message)
                view.dismissLoading()
            }).addTo(compositeDisposable)
    }

    private fun getUserData() {
        dataStore.getToken()
            .takeUntil { token -> token.isNotEmpty() }
            .subscribeOn(Schedulers.io())
            .switchMap { token ->
                apiService.getUser(token)
                    .toFlowable(BackpressureStrategy.BUFFER)
            }
            .doOnSubscribe { view.showLoading() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code == 200 && body != null) {
                    view.onGetUserSuccess(body.toDomain())
                } else {
                    view.onGetDataFailed("Failed get data")
                }
            }, { error ->
                val message = handleException(error)

                view.onGetDataFailed(message)
                view.dismissLoading()
            }).addTo(compositeDisposable)
    }

}