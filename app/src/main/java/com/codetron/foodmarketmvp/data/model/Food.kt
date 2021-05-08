package com.codetron.foodmarketmvp.data.model

data class Food(
    val id: Long,
    val image: String,
    val name: String,
    val price: Long,
    val rate: Float,
)