package com.powilliam.fluffychainsaw.data.usecases

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UpdateOneExpenseUseCase {
    suspend fun execute(id: Long, name: String?, cost: Float?, type: ExpenseType?)
}

class UpdateOneExpenseUseCaseImpl @Inject constructor(
    private val expenseDao: ExpenseDao
) : UpdateOneExpenseUseCase {
    override suspend fun execute(id: Long, name: String?, cost: Float?, type: ExpenseType?) {
        withContext(Dispatchers.IO) {
            expenseDao.updateOneExpense(id, name, cost, type)
        }
    }
}