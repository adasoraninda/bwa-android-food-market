package com.codetron.foodmarketmvp.ui.home.profile.menu

import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.model.view.profile.ProfileMenu

interface ProfileMenuContract {

    interface View {
        fun navigate(id: Long)
        fun onLogoutSuccess()
        fun onLogoutFailed(message: String)
        fun onGetMenuItemSuccess(menus: List<ProfileMenu>)
        fun onGetMenuItemFailed(message: String)
    }

    interface Presenter : BasePresenterContract {
        fun onMenuClicked(id: Long)
        fun removeToken()
    }

}