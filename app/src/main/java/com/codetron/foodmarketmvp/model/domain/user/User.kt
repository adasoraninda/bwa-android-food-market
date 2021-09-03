package com.codetron.foodmarketmvp.model.domain.user

data class User(
    var address: String,
    var city: String,
    var email: String,
    var houseNumber: String,
    var id: Int,
    var name: String,
    var phoneNumber: String,
    var profilePhotoPath: String,
    var profilePhotoUrl: String,
)