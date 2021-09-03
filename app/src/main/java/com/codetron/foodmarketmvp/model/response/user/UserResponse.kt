package com.codetron.foodmarketmvp.model.response.user


import com.codetron.foodmarketmvp.model.domain.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @Expose
    @SerializedName("address")
    val address: String?,
    @Expose
    @SerializedName("city")
    val city: String?,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long?,
    @Expose
    @SerializedName("current_team_id")
    val currentTeamId: Long?,
    @Expose
    @SerializedName("email")
    val email: String?,
    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Long?,
    @Expose
    @SerializedName("houseNumber")
    val houseNumber: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("name")
    val name: String?,
    @Expose
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @Expose
    @SerializedName("profile_photo_path")
    val profilePhotoPath: String?,
    @Expose
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String?,
    @Expose
    @SerializedName("roles")
    val roles: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
)

fun UserResponse.toDomain(): User {
    return User(
        address = this.address.orEmpty(),
        city = this.city.orEmpty(),
        email = this.email.orEmpty(),
        houseNumber = this.houseNumber.orEmpty(),
        id = this.id ?: -1,
        name = this.name.orEmpty(),
        phoneNumber = this.phoneNumber.orEmpty(),
        profilePhotoPath = this.profilePhotoPath.orEmpty(),
        profilePhotoUrl = this.profilePhotoUrl.orEmpty(),
    )
}