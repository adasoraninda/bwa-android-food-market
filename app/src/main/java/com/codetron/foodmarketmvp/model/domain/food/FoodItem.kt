package com.codetron.foodmarketmvp.model.domain.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val id: Int,
    val name: String,
    val picturePath: String,
    val price: Int,
    val rate: Int,
) : Parcelable


