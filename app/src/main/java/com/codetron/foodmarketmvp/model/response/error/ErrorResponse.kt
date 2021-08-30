package com.codetron.foodmarketmvp.model.response.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String?
)