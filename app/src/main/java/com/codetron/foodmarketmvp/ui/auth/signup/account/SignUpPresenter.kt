package com.codetron.foodmarketmvp.ui.auth.signup.account

import android.net.Uri
import com.codetron.foodmarketmvp.base.FormValidation
import com.codetron.foodmarketmvp.di.module.SignUpValidation
import com.codetron.foodmarketmvp.model.domain.user.UserRegister
import com.codetron.foodmarketmvp.model.domain.validation.SignUpFormValidation
import javax.inject.Inject

class SignUpPresenter @Inject constructor(
    private val view: SignUpContract.View,
    @SignUpValidation private val formValidation: FormValidation
) : SignUpContract.Presenter {

    override fun submitUser(fullName: String?, email: String?, password: String?, imageUri: Uri?) {
        val inputErrorMessage = checkInput(fullName, email, password)

        view.showLoading()

        if (inputErrorMessage != null) {
            view.inputFormMessage(inputErrorMessage)
            view.dismissLoading()
            return
        }

        view.validInput(
            UserRegister(
                fullName = fullName,
                email = email,
                password = password,
                imageUri = imageUri
            )
        )

        view.dismissLoading()
    }

    private fun checkInput(
        fullName: String?,
        email: String?,
        password: String?
    ): Map<String, String?>? {
        return formValidation.validateInput(
            mapOf(
                Pair(SignUpFormValidation.KEY_NAME, fullName),
                Pair(SignUpFormValidation.KEY_EMAIL, email),
                Pair(SignUpFormValidation.KEY_PASSWORD, password)
            )
        )
    }

}