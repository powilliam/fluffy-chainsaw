package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface DeleteOneExpenseUseCase {
    suspend fun execute(id: Long)
}

class DeleteOneExpenseUseCaseImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : DeleteOneExpenseUseCase {
    override suspend fun execute(id: Long) {
        withContext(Dispatchers.IO) {
            expenseDao.deleteOneExpense(id)
        }
    }
}