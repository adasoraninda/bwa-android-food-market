package com.codetron.foodmarketmvp.data.model.profile

import androidx.annotation.StringRes

data class ProfileMenu(
    val id: Long,
    @StringRes val title: Int,
)