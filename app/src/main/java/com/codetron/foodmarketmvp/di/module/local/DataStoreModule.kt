package com.codetron.foodmarketmvp.di.module.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava2.RxDataStore
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
class DataStoreModule {

    @Provides
    @Named("DataStore")
    fun providePrefFile() = "PREFERENCES_FILE"

    @Provides
    @Singleton
    fun provideDataStore(
        context: Context,
        @Named("DataStore") fileName: String
    ): RxDataStore<Preferences> {
        return RxPreferenceDataStoreBuilder(context, fileName).build()
    }

}