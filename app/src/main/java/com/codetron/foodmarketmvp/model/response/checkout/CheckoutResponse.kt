package com.codetron.foodmarketmvp.model.response.checkout


import com.codetron.foodmarketmvp.model.response.food.FoodResponse
import com.codetron.foodmarketmvp.model.response.user.UserResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckoutResponse(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Long?,
    @Expose
    @SerializedName("food")
    val food: FoodResponse?,
    @Expose
    @SerializedName("food_id")
    val foodId: Int?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("payment_url")
    val paymentUrl: String?,
    @Expose
    @SerializedName("quantity")
    val quantity: Int?,
    @Expose
    @SerializedName("status")
    val status: String?,
    @Expose
    @SerializedName("total")
    val total: Int?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?,
    @Expose
    @SerializedName("user")
    val user: UserResponse?,
    @Expose
    @SerializedName("user_id")
    val userId: Int?
)