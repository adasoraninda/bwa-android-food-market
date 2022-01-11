package com.codetron.foodmarketmvp.ui.auth.signup.address

import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.domain.user.UserRegister
import com.codetron.foodmarketmvp.model.domain.validation.SignUpAddressFormValidation
import com.codetron.foodmarketmvp.model.response.register.getToken
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.network.FoodMarketApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@ExperimentalCoroutinesApi
class SignUpAddressPresenter(
    private val view: SignUpAddressContract.View,
    private val dataStore: UserDataStore,
    private val apiService: FoodMarketApi,
    private val formValidation: FormValidation,
    private val userRegister: UserRegister?
) : SignUpAddressContract.Presenter {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        view.dismissLoading()
    }

    override fun unSubscribe() {
        compositeDisposable.clear()
    }

    override fun submitRegister(
        phoneNumber: String?,
        address: String?,
        houseNumber: String?,
        city: String?
    ) {
        val inputErrorMessage = checkInput(phoneNumber, address, houseNumber, city)

        if (inputErrorMessage != null) {
            view.inputFormMessage(inputErrorMessage)
            return
        }

        val dataUser = userRegister?.copy(
            phoneNumber = phoneNumber,
            address = address,
            houseNumber = houseNumber,
            city = city
        )

        view.showLoading()

        dataUser?.let { submitRegisterAccount(it) }
    }

    private fun submitRegisterAccount(dataUser: UserRegister) {
        val disposable = apiService.userRegister(
            dataUser.fullName.toString(),
            dataUser.email.toString(),
            dataUser.password.toString(),
            dataUser.password.toString(),
            dataUser.address.toString(),
            dataUser.city.toString(),
            dataUser.houseNumber.toString(),
            dataUser.phoneNumber.toString()
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code == 200 && body?.user != null) {
                    submitToken(body.getToken()) { isSuccess ->
                        if (isSuccess) {
                            submitRegisterPhoto(body.getToken()) { path, error ->
                                if (path == null) {
                                    view.onRegisterFailed(error.toString())
                                }
                            }
                            view.onRegisterSuccess(body.user.toDomain())
                        } else {
                            view.onRegisterFailed("Fail saving token")
                        }
                    }
                } else {
                    view.onRegisterFailed(response.meta?.message.toString())
                }

                view.dismissLoading()
            }, { error ->
                val message = handleException(error)

                view.onRegisterFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    private fun submitRegisterPhoto(
        token: String?,
        callback: (imagePath: String?, errorMessage: String?) -> Unit
    ) {
        if (userRegister?.imageUri == null) {
            callback.invoke(null, "Image is empty")
            return
        }

        if (token == null) {
            callback.invoke(null, "Token is empty")
            return
        }

        val imageUri = userRegister.imageUri
        val imagePath = File(imageUri.path.toString())
        val imageRequestBody = imagePath.asRequestBody("multipart/form-data".toMediaType())
        val imageField =
            MultipartBody.Part.createFormData("file", imagePath.name, imageRequestBody)

        val disposable = apiService
            .photoRegister(token, imageField)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code == 200 && body?.isNullOrEmpty()?.not() as Boolean) {
                    callback.invoke(body[0], null)
                } else {
                    view.onRegisterFailed(response.meta?.message.toString())
                }

                view.dismissLoading()
            }, { error ->
                callback.invoke(null, error.message.toString())

                val message = handleException(error)

                view.onRegisterFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    private fun submitToken(token: String, callback: (isSuccess: Boolean) -> Unit) {
        val disposable = dataStore.saveToken(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ prefs ->
                callback.invoke(!prefs[UserDataStore.PREFERENCES_TOKEN].isNullOrEmpty())
            }, { error ->
                callback.invoke(false)

                val message = handleException(error)

                view.onRegisterFailed(message)
                view.dismissLoading()
            })

        compositeDisposable.add(disposable)
    }

    private fun checkInput(
        phoneNumber: String?,
        address: String?,
        houseNumber: String?,
        city: String?
    ): Map<String, String?>? {
        return formValidation.validateInput(
            mapOf(
                Pair(SignUpAddressFormValidation.KEY_PHONE, phoneNumber),
                Pair(SignUpAddressFormValidation.KEY_ADDRESS, address),
                Pair(SignUpAddressFormValidation.KEY_HOUSE, houseNumber),
                Pair(SignUpAddressFormValidation.KEY_CITY, city),
            )
        )
    }
}