package com.codetron.foodmarketmvp.ui.home.dashboard

import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.response.food.FoodResponse
import com.codetron.foodmarketmvp.model.response.food.toItemDomain
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class DashboardPresenter (
    private val view: DashboardContract.View,
    private val apiService: FoodMarketApi,
    private val dataStore: UserDataStore
) : DashboardContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        getToken()
        getAllFood()
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    override fun getAllFood() {
        view.showLoading()

        val disposable = apiService.getAllFood()
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
            })

        compositeDisposable.add(disposable)
    }

    override fun getUser(token: String) {
        val disposable = apiService.getUser(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code

                if (code == 200) {
                    val body = response.data
                    body?.toDomain()?.let { view.onGetUserSuccess(it) }
                }

            }, { error ->
                val message = handleException(error)

                view.onGetDataFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    private fun getToken() {
        val disposable = dataStore.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                if (!token.isNullOrEmpty()) {
                    getUser(token)
                } else {
                    view.onGetDataFailed("Token is empty")
                }
            }, { error ->
                val message = handleException(error)

                view.onGetDataFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

}