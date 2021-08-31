package com.codetron.foodmarketmvp.ui.auth.signup.address

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.user.User

interface SignUpAddressContract {

    interface View : BaseViewContract {
        fun onRegisterSuccess(user: User)
        fun onRegisterFailed(message: String)
        fun inputFormMessage(messageMap: Map<String, String?>)
    }

    interface Presenter : BasePresenterContract {
        fun submitRegister(
            phoneNumber: String?,
            address: String?,
            houseNumber: String?,
            city: String?
        )
    }

}