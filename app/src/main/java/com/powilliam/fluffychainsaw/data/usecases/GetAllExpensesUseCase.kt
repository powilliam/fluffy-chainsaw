package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetAllExpensesUseCase {
    suspend fun execute(): Flow<List<Expense>>
}

class GetAllExpensesUseCaseImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : GetAllExpensesUseCase {
    override suspend fun execute(): Flow<List<Expense>> {
        return withContext(Dispatchers.IO) {
            expenseDao.getAllExpenses()
        }
    }
}