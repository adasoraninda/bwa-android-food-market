package com.codetron.foodmarketmvp.ui.auth.signin

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.model.validation.SignInFormValidation
import com.codetron.foodmarketmvp.network.RetrofitInstance
import com.codetron.foodmarketmvp.util.ErrorResponseUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.HttpException

@ExperimentalCoroutinesApi
class SignInPresenter(
    private val view: SignInContract.View,
    private val dataStore: UserDataStore,
    private val formValidation: FormValidation
) : SignInContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }
    private val retrofit by lazy { RetrofitInstance() }

    override fun subscribe() {
        view.dismissLoading()
    }

    override fun submitLogin(email: String?, password: String?) {
        val inputErrorMessage = checkInput(email, password)

        if (inputErrorMessage != null) {
            view.inputFormMessage(inputErrorMessage)
            return
        }

        view.showLoading()

        val disposable = retrofit.getApi().userLogin(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta.code
                val body = response.data

                if (code == 200 && body.user != null) {
                    compositeDisposable.add(
                        dataStore.saveToken(body.accessToken)
                            .subscribe({ prefs ->
                                if (prefs[UserDataStore.PREFERENCES_TOKEN].toBoolean()) {
                                    view.onLoginSuccess(body.user.toDomain())
                                }
                            }, { error ->
                                view.onLoginFailed(error.message.toString())
                            })
                    )
                } else {
                    view.onLoginFailed(response.meta.message)
                }

                view.dismissLoading()
            }, { error ->
                var message: String = error.message.toString()

                if (error is HttpException) {
                    val errorResponse = error.response()?.errorBody()?.string()
                    if (errorResponse != null) {
                        message = ErrorResponseUtil(errorResponse).getMeta().message
                    }
                }

                view.onLoginFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
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
