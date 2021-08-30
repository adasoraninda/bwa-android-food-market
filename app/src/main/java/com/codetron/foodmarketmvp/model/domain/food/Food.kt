package com.codetron.foodmarketmvp.model.domain.food

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val id: Long,
    val image: String,
    val name: String,
    val description: String,
    val ingredients: String,
    val price: Long,
    val rate: Float,
) : Parcelable