package com.codetron.foodmarketmvp.ui.splash

import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SplashPresenter(
    private val view: SplashContract.View,
    private val dataStore: UserDataStore,
) : SplashContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        val disposable = dataStore.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                if (token != null) {
                    view.navigate(SplashActivity.KEY_HOME)
                } else {
                    view.navigate(SplashActivity.KEY_LOGIN)
                }
            }, {
                view.navigate(SplashActivity.KEY_LOGIN)
            })

        compositeDisposable.add(disposable)
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }
}