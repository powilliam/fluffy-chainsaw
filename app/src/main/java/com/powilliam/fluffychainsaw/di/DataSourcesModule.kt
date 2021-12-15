package com.powilliam.fluffychainsaw.di

import android.content.Context
import com.powilliam.fluffychainsaw.data.datasources.ExpensesLocalDataSource
import com.powilliam.fluffychainsaw.data.datasources.ExpensesLocalDataSourceImpl
import com.powilliam.fluffychainsaw.data.datasources.SettingsLocalDataSource
import com.powilliam.fluffychainsaw.data.datasources.SettingsLocalDataSourceImpl
import com.powilliam.fluffychainsaw.data.persistence.FluffyChainsawDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourcesModule {
    @Singleton
    @Provides
    fun provideExpensesLocalDataSource(database: FluffyChainsawDatabase): ExpensesLocalDataSource =
        ExpensesLocalDataSourceImpl(database.expenseDao())

    @Singleton
    @Provides
    fun provideSettingsLocalDataSource(@ApplicationContext context: Context): SettingsLocalDataSource =
        SettingsLocalDataSourceImpl(context)
}