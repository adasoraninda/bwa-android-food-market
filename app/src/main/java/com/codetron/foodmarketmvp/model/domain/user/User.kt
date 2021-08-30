package com.codetron.foodmarketmvp.model.domain.user

data class User(
    var address: String,
    var city: String,
    var createdAt: Long,
    var currentTeamId: Long,
    var email: String,
    var emailVerifiedAt: Long,
    var houseNumber: String,
    var id: Int,
    var name: String,
    var phoneNumber: String,
    var profilePhotoPath: String,
    var profilePhotoUrl: String,
    var roles: String,
    var updatedAt: Long
)