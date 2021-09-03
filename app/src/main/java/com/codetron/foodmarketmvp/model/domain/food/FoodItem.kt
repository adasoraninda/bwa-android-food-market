package com.codetron.foodmarketmvp.model.domain.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    var id: Int,
    var name: String,
    var picturePath: String,
    var price: Int,
    var rate: Int,
) : Parcelable


