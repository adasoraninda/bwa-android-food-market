package com.codetron.foodmarketmvp.di.module

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava2.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava2.RxDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Named

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Named("DataStore")
    fun providePrefFile() = "PREFERENCES_FILE"

    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context,
        @Named("DataStore") fileName: String
    ): RxDataStore<Preferences> {
        return RxPreferenceDataStoreBuilder(context, fileName).build()
    }

}