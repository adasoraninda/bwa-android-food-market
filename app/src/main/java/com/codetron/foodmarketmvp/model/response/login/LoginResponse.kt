package com.codetron.foodmarketmvp.model.response.login


import com.codetron.foodmarketmvp.model.response.user.UserResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("access_token")
    val accessToken: String,
    @Expose
    @SerializedName("token_type")
    val tokenType: String,
    @Expose
    @SerializedName("user")
    val user: UserResponse?
)