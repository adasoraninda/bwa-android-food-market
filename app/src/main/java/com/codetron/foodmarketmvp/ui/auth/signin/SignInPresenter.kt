package com.codetron.foodmarketmvp.ui.auth.signin

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.response.login.getToken
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.model.validation.SignInFormValidation
import com.codetron.foodmarketmvp.network.FoodMarketApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SignInPresenter(
    private val view: SignInContract.View,
    private val dataStore: UserDataStore,
    private val serviceApi: FoodMarketApi,
    private val formValidation: FormValidation
) : SignInContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        view.dismissLoading()
    }

    override fun submitLogin(email: String?, password: String?) {
        val inputErrorMessage = checkInput(email, password)

        if (inputErrorMessage != null) {
            view.inputFormMessage(inputErrorMessage)
            return
        }

        serviceApi.userLogin(email!!, password!!)
            .doOnSubscribe { view.showLoading() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code != 200 && body?.user == null) {
                    view.onLoginFailed(response.meta?.message.toString())
                } else {
                    dataStore.saveToken(body!!.getToken())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ prefs ->
                            if (!prefs[UserDataStore.PREFERENCES_TOKEN].isNullOrEmpty()) {
                                view.onLoginSuccess(body.user!!.toDomain())
                            }
                        }, { error ->
                            view.onLoginFailed(error.message.toString())
                        }).addTo(compositeDisposable)
                }

                view.dismissLoading()
            }, { error ->
                val message = handleException(error)

                view.onLoginFailed(message)
                view.dismissLoading()
            }).addTo(compositeDisposable)
    }

    private fun checkInput(
        email: String?,
        password: String?
    ): Map<String, String?>? {
        return formValidation.validateInput(
            mapOf(
                Pair(SignInFormValidation.KEY_EMAIL, email),
                Pair(SignInFormValidation.KEY_PASSWORD, password)
            )
        )
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

}
