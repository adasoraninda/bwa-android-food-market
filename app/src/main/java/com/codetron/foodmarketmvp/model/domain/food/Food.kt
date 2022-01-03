package com.codetron.foodmarketmvp.model.domain.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val description: String,
    val id: Int,
    val ingredients: String,
    val name: String,
    val picturePath: String,
    val price: Int,
    val rate: Int,
    val types: String,
) : Parcelable