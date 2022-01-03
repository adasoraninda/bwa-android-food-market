package com.codetron.foodmarketmvp.model.domain.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.rxjava2.RxDataStore
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class UserDataStore @Inject constructor(private val dataStore: RxDataStore<Preferences>) {

    fun saveToken(token: String): Single<Preferences> {
        return dataStore.updateDataAsync { pref ->
            val mutablePreferences = pref.toMutablePreferences()
            mutablePreferences[PREFERENCES_TOKEN] = token
            Single.just(mutablePreferences)
        }
    }

    fun getToken(): Flowable<String?> {
        return dataStore.data().map { pref -> pref[PREFERENCES_TOKEN] }
    }

    fun removeToken(): Single<Preferences> {
        return dataStore.updateDataAsync { pref ->
            val mutablePreferences = pref.toMutablePreferences()
            mutablePreferences[PREFERENCES_TOKEN] = ""
            Single.just(mutablePreferences)
        }
    }

    companion object {
        val PREFERENCES_TOKEN = stringPreferencesKey("PREFERENCES_TOKEN")
    }

}