package com.codetron.foodmarketmvp.ui.home.profile

import android.util.Log
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProfilePresenter(
    private val view: ProfileContract.View,
    private val dataStore: UserDataStore,
    private val serviceApi: FoodMarketApi
) : ProfileContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        view.initState()
        getUserData()
    }

    private fun getUserData() {
        dataStore.getToken()
            .takeUntil { token -> token.isNotEmpty() }
            .doOnSubscribe { view.showLoading() }
            .subscribeOn(Schedulers.io())
            .doOnEach { Log.d("TOKEN", "${it.value}") }
            .switchMap { token ->
                serviceApi
                    .getUser(token)
                    .toFlowable(BackpressureStrategy.BUFFER)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code != 200 && body == null) {
                    view.onGetUserDataFailed("Failed get data")
                } else {
                    view.onGetUserDataSuccess(body!!.toDomain())
                }
                view.dismissLoading()
            }, { error ->
                val message = handleException(error)
                view.onGetUserDataFailed(message)
                view.dismissLoading()
            }).addTo(compositeDisposable)
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }
}