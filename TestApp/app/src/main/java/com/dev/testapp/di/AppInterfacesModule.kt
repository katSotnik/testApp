package com.dev.testapp.di

import com.dev.testapp.data.repository.ISettingsRepository
import com.dev.testapp.data.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppInterfacesModule {
    //region LocalApi
    //endregion

    //region Repositories
    @Binds
    abstract fun bindSettingsRepository(impl: SettingsRepository): ISettingsRepository
    //endregion
}