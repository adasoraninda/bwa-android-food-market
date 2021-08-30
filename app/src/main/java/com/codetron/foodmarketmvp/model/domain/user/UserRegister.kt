package com.codetron.foodmarketmvp.model.domain.user

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRegister(
    var fullName: String? = null,
    var email: String? = null,
    var password: String? = null,
    var phoneNumber: String? = null,
    var address: String? = null,
    var houseNumber: String? = null,
    var city: String? = null,
    var imageUri: Uri? = null
) : Parcelable