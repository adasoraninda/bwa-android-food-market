package com.codetron.foodmarketmvp.model.domain.view.profile

import androidx.annotation.StringRes

data class ProfileMenu(
    val id: Long,
    @StringRes val title: Int,
)