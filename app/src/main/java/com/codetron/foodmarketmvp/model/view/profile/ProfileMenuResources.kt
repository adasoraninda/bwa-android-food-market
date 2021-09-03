package com.codetron.foodmarketmvp.model.view.profile

import com.codetron.foodmarketmvp.R

object ProfileMenuResources {

    fun getAccountResources(): List<ProfileMenu> {
        return listOf(
            ProfileMenu(
                id = 11L,
                title = R.string.edit_profile,
            ),
            ProfileMenu(
                id = 12L,
                title = R.string.home_address,
            ),
            ProfileMenu(
                id = 13L,
                title = R.string.security,
            ),
            ProfileMenu(
                id = 14L,
                title = R.string.payments,
            ),
            ProfileMenu(
                id = 15L,
                title = R.string.logout,
            ),
        )
    }

    fun getFoodMarketResources(): List<ProfileMenu> {
        return listOf(
            ProfileMenu(
                id = 21L,
                title = R.string.rate_app,
            ),
            ProfileMenu(
                id = 22L,
                title = R.string.help_center,
            ),
            ProfileMenu(
                id = 23L,
                title = R.string.privacy_policy,
            ),
            ProfileMenu(
                id = 24L,
                title = R.string.terms_conditions,
            ),
        )
    }

}