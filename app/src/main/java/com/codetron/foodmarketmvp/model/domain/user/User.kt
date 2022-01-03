package com.codetron.foodmarketmvp.model.domain.user

data class User(
    val address: String,
    val city: String,
    val email: String,
    val houseNumber: String,
    val id: Int,
    val name: String,
    val phoneNumber: String,
    val profilePhotoPath: String,
    val profilePhotoUrl: String,
)