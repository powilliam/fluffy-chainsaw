package com.powilliam.fluffychainsaw.di

import com.powilliam.fluffychainsaw.data.datasources.ExpensesLocalDataSource
import com.powilliam.fluffychainsaw.data.datasources.SettingsLocalDataSource
import com.powilliam.fluffychainsaw.data.repositories.ExpensesRepository
import com.powilliam.fluffychainsaw.data.repositories.ExpensesRepositoryImpl
import com.powilliam.fluffychainsaw.data.repositories.SettingsRepository
import com.powilliam.fluffychainsaw.data.repositories.SettingsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
    @Provides
    fun provideExpensesRepository(expensesLocalDataSource: ExpensesLocalDataSource): ExpensesRepository =
        ExpensesRepositoryImpl(expensesLocalDataSource)

    @Provides
    fun provideSettingsRepository(settingsLocalDataSource: SettingsLocalDataSource): SettingsRepository =
        SettingsRepositoryImpl(settingsLocalDataSource)
}