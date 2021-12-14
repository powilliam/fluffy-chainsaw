package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DeleteAllVariableExpensesUseCase {
    suspend fun execute()
}

class DeleteAllVariableExpensesUseCaseImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : DeleteAllVariableExpensesUseCase {
    override suspend fun execute() {
        withContext(Dispatchers.IO) {
            expenseDao.deleteAllExpensesByType(ExpenseType.Variable)
        }
    }
}