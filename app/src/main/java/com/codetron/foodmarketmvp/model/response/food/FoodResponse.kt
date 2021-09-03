package com.codetron.foodmarketmvp.model.response.food


import com.codetron.foodmarketmvp.model.domain.food.Food
import com.codetron.foodmarketmvp.model.domain.food.FoodItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Long?,
    @Expose
    @SerializedName("description")
    val description: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("picturePath")
    val picturePath: String?,
    @Expose
    @SerializedName("price")
    val price: Int?,
    @Expose
    @SerializedName("rate")
    val rate: Int?,
    @Expose
    @SerializedName("types")
    val types: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
)

fun FoodResponse.toItemDomain(): FoodItem {
    return FoodItem(
        this.id ?: -1,
        this.name.orEmpty(),
        this.picturePath.orEmpty(),
        this.price ?: 0,
        this.rate ?: 0,
    )
}

fun FoodResponse.toDetailDomain(): Food {
    return Food(
        this.description.orEmpty(),
        this.id ?: -1,
        this.ingredients.orEmpty(),
        this.name.orEmpty(),
        this.picturePath.orEmpty(),
        this.price ?: 0,
        this.rate ?: 0,
        this.types.orEmpty()
    )
}

