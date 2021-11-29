package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetOneExpenseUseCase {
    suspend fun execute(id: Long): Expense
}

class GetOneExpenseUseCaseImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : GetOneExpenseUseCase {
    override suspend fun execute(id: Long): Expense {
        return withContext(Dispatchers.IO) {
            expenseDao.getOneExpense(id)
        }
    }
}