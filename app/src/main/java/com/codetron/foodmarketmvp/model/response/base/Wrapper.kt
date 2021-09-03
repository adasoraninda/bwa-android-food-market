package com.codetron.foodmarketmvp.model.response.base


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @Expose
    @SerializedName("meta")
    val meta: Meta?,
    @Expose
    @SerializedName("data")
    val `data`: T?,
)