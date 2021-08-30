package com.codetron.foodmarketmvp.model.validation

import android.util.Patterns
import com.codetron.foodmarketmvp.base.FormValidation
import javax.inject.Inject


class SignInFormValidation @Inject constructor() : FormValidation {
    companion object {
        private const val PASSWORD_MIN_LENGTH = 8
        const val KEY_EMAIL = "EMAIL"
        const val KEY_PASSWORD = "PASSWORD"

        private const val EMAIL_INVALID_ERROR = "Email not valid"
        private const val PASSWORD_INVALID_ERROR = "Password not valid"
        private const val PASSWORD_LENGTH_ERROR = "Password length at least 8 characters"
    }

    override fun validateInput(input: Map<String, String?>): Map<String, String?>? {
        emailValidation(input[KEY_EMAIL])?.let { return mapOf(Pair(KEY_EMAIL, it)) }
        passwordValidation(input[KEY_PASSWORD])?.let { return mapOf(Pair(KEY_PASSWORD, it)) }
        return null
    }

    private fun emailValidation(email: String?): String? {
        if (email.isNullOrEmpty()
            || email.isNullOrBlank()
            || email.matches(Patterns.EMAIL_ADDRESS.toRegex()).not()
        ) {
            return EMAIL_INVALID_ERROR
        }

        return null
    }

    private fun passwordValidation(password: String?): String? {
        if (password.isNullOrEmpty() || password.isNullOrBlank()) {
            return PASSWORD_INVALID_ERROR
        }

        if (password.length < PASSWORD_MIN_LENGTH) {
            return PASSWORD_LENGTH_ERROR
        }

        return null
    }
}