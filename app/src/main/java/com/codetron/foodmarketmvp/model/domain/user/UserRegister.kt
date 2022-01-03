package com.codetron.foodmarketmvp.model.domain.user

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRegister(
    val fullName: String? = null,
    val email: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val houseNumber: String? = null,
    val city: String? = null,
    val imageUri: Uri? = null
) : Parcelable