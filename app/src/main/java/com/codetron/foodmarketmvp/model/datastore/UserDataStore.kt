package com.codetron.foodmarketmvp.model.datastore

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

    companion object {
        val PREFERENCES_TOKEN = stringPreferencesKey("PREFERENCES_TOKEN")
        val PREFERENCES_USER = stringPreferencesKey("PREFERENCES_USER")
    }

    fun saveToken(token: String): Single<Preferences> {
        return dataStore.updateDataAsync { prefsIn ->
            val mutablePreferences = prefsIn.toMutablePreferences()
            mutablePreferences[PREFERENCES_TOKEN] = token
            Single.just(mutablePreferences)
        }
    }

    fun getToken(): Flowable<String?> {
        return dataStore.data().map { prefs -> prefs[PREFERENCES_TOKEN] }
    }

    fun saveUser(username: String): Single<Preferences> {
        return dataStore.updateDataAsync { prefsIn ->
            val mutablePreferences = prefsIn.toMutablePreferences()
            mutablePreferences[PREFERENCES_USER] = username
            Single.just(mutablePreferences)
        }
    }

    fun getUser(): Flowable<String> {
        return dataStore.data().map { prefs -> prefs[PREFERENCES_USER] }
    }

}