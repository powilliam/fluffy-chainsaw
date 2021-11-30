package com.powilliam.fluffychainsaw.di

import com.powilliam.fluffychainsaw.data.usecases.*
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
    abstract fun bindGetOneExpenseUseCase(
        getOneExpenseUseCaseImpl: GetOneExpenseUseCaseImpl
    ): GetOneExpenseUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindUpdateOneExpenseUseCase(
        updateOneExpenseUseCaseImpl: UpdateOneExpenseUseCaseImpl
    ): UpdateOneExpenseUseCase

    @ViewModelScoped
    @Binds
    abstract fun bindInsertManyExpensesUseCase(
        insertManyExpensesUseCaseImpl: InsertManyExpensesUseCaseImpl
    ): InsertManyExpensesUseCase
}