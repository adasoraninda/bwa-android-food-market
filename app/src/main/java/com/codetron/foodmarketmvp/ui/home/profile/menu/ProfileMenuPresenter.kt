package com.codetron.foodmarketmvp.ui.home.profile.menu

import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.model.domain.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.domain.view.profile.ProfileMenu
import com.codetron.foodmarketmvp.model.domain.view.profile.ProfileMenuResources
import com.codetron.foodmarketmvp.network.FoodMarketApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProfileMenuPresenter @AssistedInject constructor(
    private val view: ProfileMenuContract.View,
    private val serviceApi: FoodMarketApi,
    private val dataStore: UserDataStore,
    @Assisted private val type: ProfileMenuType
) : ProfileMenuContract.Presenter {

    @AssistedFactory
    interface Factory {
        fun create(type: ProfileMenuType): ProfileMenuPresenter
    }

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun subscribe() {
        initListMenu()
    }

    override fun unSubscribe() {
        compositeDisposable.dispose()
    }

    override fun onMenuClicked(id: Long) {
        getListMenuResources()
            .find { menu -> menu.id == id }
            ?.let { menu ->
                if (menu.title == R.string.logout) {
                    getToken()
                } else {
                    view.navigate(id)
                }
            }
    }

    private fun initListMenu() {
        val menus = getListMenuResources()

        if (menus.isEmpty()) {
            view.onGetMenuItemFailed("List menu is empty")
        } else {
            view.onGetMenuItemSuccess(menus)
        }
    }

    private fun getListMenuResources(): List<ProfileMenu> {
        return when (type) {
            ProfileMenuType.ACCOUNT -> ProfileMenuResources.getAccountResources()
            ProfileMenuType.FOOD_MARKET -> ProfileMenuResources.getFoodMarketResources()
        }
    }

    private fun getToken() {
        val disposable = dataStore.getToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { token ->
                logout(token)
            }

        compositeDisposable.add(disposable)
    }

    private fun logout(token: String?) {
        if (token.isNullOrEmpty()) {
            view.onLogoutFailed("Token is empty")
            return
        }

        val disposable = serviceApi.userLogout(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code == 200 && body != null && body == true) {
                    view.onLogoutSuccess()
                    removeToken()
                } else {
                    view.onLogoutFailed("Logout failed")
                }
            }, { error ->
                val message = handleException(error)

                view.onLogoutFailed(message)
            })

        compositeDisposable.add(disposable)
    }

    override fun removeToken() {
        val disposable = dataStore.removeToken()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { prefs ->
                if (!prefs[UserDataStore.PREFERENCES_TOKEN].isNullOrEmpty()) {
                    view.onLogoutFailed("Token fail to removed")
                }
            }

        compositeDisposable.add(disposable)
    }
}