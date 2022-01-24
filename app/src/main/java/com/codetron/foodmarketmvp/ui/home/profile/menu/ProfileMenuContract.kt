package com.codetron.foodmarketmvp.ui.home.profile.menu

import androidx.datastore.preferences.core.Preferences
import com.codetron.foodmarketmvp.base.BasePresenterContract
import com.codetron.foodmarketmvp.model.view.profile.ProfileMenu
import io.reactivex.Single

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
    }

}