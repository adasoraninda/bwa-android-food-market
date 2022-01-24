package com.codetron.foodmarketmvp.ui.home.profile.menu

import com.codetron.foodmarketmvp.R
import com.codetron.foodmarketmvp.model.datastore.UserDataStore
import com.codetron.foodmarketmvp.model.view.profile.ProfileMenu
import com.codetron.foodmarketmvp.model.view.profile.ProfileMenuResources
import com.codetron.foodmarketmvp.network.FoodMarketApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ProfileMenuPresenter @AssistedInject constructor(
    private val view: ProfileMenuContract.View,
    private val serviceApi: FoodMarketApi,
    private val dataStore: UserDataStore,
    @Assisted private val type: ProfileMenuType
) : ProfileMenuContract.Presenter {

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
                    logout()
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

    private fun logout() {
        dataStore.getToken()
            .subscribeOn(Schedulers.io())
            .takeUntil { token -> token.isNotEmpty() }
            .switchMap { serviceApi.userLogout(it).toFlowable(BackpressureStrategy.BUFFER) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val code = response.meta?.code
                val body = response.data

                if (code != 200) {
                    view.onLogoutFailed("Logout failed")
                }

                if (body != null && body == true) {
                    dataStore.removeToken()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { prefs ->
                            if (!prefs[UserDataStore.PREFERENCES_TOKEN].isNullOrEmpty()) {
                                view.onLogoutFailed("Token fail to removed")
                            } else {
                                view.onLogoutSuccess()
                            }
                        }.addTo(compositeDisposable)
                }
            }, { error ->
                val message = handleException(error)
                view.onLogoutFailed(message)
            }).addTo(compositeDisposable)
    }

    @AssistedFactory
    interface Factory {
        fun create(type: ProfileMenuType): ProfileMenuPresenter
    }
}