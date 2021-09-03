package com.codetron.foodmarketmvp.model.domain.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    var description: String,
    var id: Int,
    var ingredients: String,
    var name: String,
    var picturePath: String,
    var price: Int,
    var rate: Int,
    var types: String,
) : Parcelable