package com.powilliam.fluffychainsaw.di

import com.powilliam.fluffychainsaw.data.usecases.GetAllExpensesUseCase
import com.powilliam.fluffychainsaw.data.usecases.GetAllExpensesUseCaseImpl
import com.powilliam.fluffychainsaw.data.usecases.InsertManyExpensesUseCase
import com.powilliam.fluffychainsaw.data.usecases.InsertManyExpensesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCasesModule {
    @ViewModelScoped
    @Binds
    abstract fun bindGetAllExpensesUseCase(
        getAllExpensesUseCaseImpl: GetAllExpensesUseCaseImpl
    ): GetAllExpensesUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindInsertManyExpensesUseCase(
        insertManyExpensesUseCaseImpl: InsertManyExpensesUseCaseImpl
    ): InsertManyExpensesUseCase
}