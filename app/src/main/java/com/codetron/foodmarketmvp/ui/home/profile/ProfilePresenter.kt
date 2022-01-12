package com.codetron.foodmarketmvp.ui.home.profile

import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
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
        view.showLoading()
        getToken()
    }

    private fun getUser(token: String) {
        val disposable = serviceApi.getUser(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code

                if (code == 200) {
                    val body = response.data
                    body?.toDomain()?.let { view.onGetUserDataSuccess(it) }
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
        val disposable = dataStore.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                if (!token.isNullOrEmpty()) {
                    getUser(token)
                } else {
                    view.onGetUserDataFailed("Token is empty")
                }
            }, { error ->
                val message = handleException(error)

                view.onGetUserDataFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }
}