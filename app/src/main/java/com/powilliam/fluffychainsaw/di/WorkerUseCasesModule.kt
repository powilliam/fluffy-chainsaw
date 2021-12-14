package com.powilliam.fluffychainsaw.di

import android.content.Context
import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkerUseCasesModule {
    @Singleton
    @Provides
    fun provideDeleteAllVariableExpensesUseCase(expenseDao: ExpenseDao): DeleteAllVariableExpensesUseCase {
        return DeleteAllVariableExpensesUseCaseImpl(expenseDao)
    }

    @Singleton
    @Provides
    fun provideGetMonthEndingFromSettingsDataStoreUseCase(
        @ApplicationContext context: Context
    ): GetMonthEndingFromSettingsDataStoreUseCase {
        return GetMonthEndingFromSettingsDataStoreUseCaseImpl(context)
    }

    @Singleton
    @Provides
    fun provideUpdateMonthEndingFromSettingsDataStoreUseCase(
        @ApplicationContext context: Context
    ): UpdateMonthEndingFromSettingsDataStoreUseCase {
        return UpdateMonthEndingFromSettingsDataStoreUseCaseImpl(context)
    }
}