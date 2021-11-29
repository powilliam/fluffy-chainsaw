package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface InsertManyExpensesUseCase {
    suspend fun execute(vararg expense: Expense)
}

class InsertManyExpensesUseCaseImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : InsertManyExpensesUseCase {
    override suspend fun execute(vararg expense: Expense) {
        withContext(Dispatchers.IO) {
            expenseDao.insertManyExpenses(*expense)
        }
    }
}