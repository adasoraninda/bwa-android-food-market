package com.codetron.foodmarketmvp.ui.splash

import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class SplashPresenter(
    private val view: SplashContract.View,
    private val dataStore: UserDataStore,
) : SplashContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun subscribe() {
        dataStore.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
                view.navigate(!token.isNullOrEmpty())
            }, {
                view.navigate(false)
            }).addTo(compositeDisposable)
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }
}