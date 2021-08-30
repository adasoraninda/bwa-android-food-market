package com.codetron.foodmarketmvp.ui.auth.signup.account

import android.net.Uri
import com.codetron.foodmarketmvp.base.BaseViewContract
import com.codetron.foodmarketmvp.model.domain.user.User
import com.codetron.foodmarketmvp.model.domain.user.UserRegister

interface SignUpContract {

    interface View : BaseViewContract {
        fun validInput(user: UserRegister)
        fun inputFormMessage(messageMap: Map<String, String?>)
    }

    interface Presenter {
        fun submitUser(
            fullName: String?,
            email: String?,
            password: String?,
            imageUri: Uri?
        )
    }

}