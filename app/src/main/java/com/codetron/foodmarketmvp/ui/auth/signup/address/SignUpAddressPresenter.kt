package com.codetron.foodmarketmvp.ui.auth.signup.address

import android.net.Uri
import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.common.SignUpAddressValidation
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.domain.user.UserRegister
import com.codetron.foodmarketmvp.model.response.base.Wrapper
import com.codetron.foodmarketmvp.model.response.register.RegisterResponse
import com.codetron.foodmarketmvp.model.response.register.getToken
import com.codetron.foodmarketmvp.model.response.user.toDomain
import com.codetron.foodmarketmvp.model.validation.SignUpAddressFormValidation
import com.codetron.foodmarketmvp.network.FoodMarketApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@ExperimentalCoroutinesApi
class SignUpAddressPresenter @AssistedInject constructor(
    private val view: SignUpAddressContract.View,
    private val dataStore: UserDataStore,
    private val apiService: FoodMarketApi,
    @SignUpAddressValidation private val formValidation: FormValidation,
    @Assisted private val userRegister: UserRegister?
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

        if (dataUser != null) {
            submitRegisterAccount(dataUser)
                .doOnSubscribe { view.showLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    val code = response.meta?.code
                    val body = response.data ?: throw IllegalStateException()
                    val user = body.user ?: throw  IllegalStateException()

                    if (code == 200) {
                        Observable.combineLatest(
                            saveToken(body.getToken()),
                            submitRegisterPhoto(body.getToken(), dataUser.imageUri)
                        ) { isTokenExist, listPathImage ->
                            isTokenExist || listPathImage.isNotEmpty()
                        }.subscribe { registered ->
                            if (registered) {
                                view.onRegisterSuccess(user.toDomain())
                            } else {
                                view.onRegisterFailed("Failed save token")
                            }
                        }.addTo(compositeDisposable)
                    }

                    view.dismissLoading()
                }, { error ->
                    val message = handleException(error)

                    view.onRegisterFailed(message)
                    view.dismissLoading()
                }).addTo(compositeDisposable)
        }
    }

    private fun submitRegisterAccount(dataUser: UserRegister): Observable<Wrapper<RegisterResponse>> {
        return apiService.userRegister(
            dataUser.fullName.toString(),
            dataUser.email.toString(),
            dataUser.password.toString(),
            dataUser.password.toString(),
            dataUser.address.toString(),
            dataUser.city.toString(),
            dataUser.houseNumber.toString(),
            dataUser.phoneNumber.toString()
        ).subscribeOn(Schedulers.io())
    }

    private fun submitRegisterPhoto(
        token: String,
        imageUri: Uri?,
    ): Observable<List<String>> {

        if (imageUri == null) {
            return Observable.just(emptyList())
        }

        val imagePath = File(imageUri.path.toString())
        val imageRequestBody = imagePath.asRequestBody("multipart/form-data".toMediaType())
        val imageField =
            MultipartBody.Part.createFormData("file", imagePath.name, imageRequestBody)

        return apiService.photoRegister(token, imageField)
            .subscribeOn(Schedulers.io())
            .map { response -> response.data ?: emptyList() }
    }

    private fun saveToken(token: String): Observable<Boolean> {
        return dataStore.saveToken(token)
            .subscribeOn(Schedulers.io())
            .map { prefs -> !prefs[UserDataStore.PREFERENCES_TOKEN].isNullOrEmpty() }
            .toObservable()
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

    @AssistedFactory
    interface Factory {
        fun create(userRegister: UserRegister?): SignUpAddressPresenter
    }

}