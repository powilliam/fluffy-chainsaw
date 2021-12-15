package com.powilliam.fluffychainsaw.data.datasources

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ExpensesLocalDataSource {
    suspend fun getAllExpenses(): Flow<List<Expense>>
    suspend fun getOneExpense(expenseId: Long): Expense
    suspend fun updateOneExpense(id: Long, name: String?, cost: Float?, type: ExpenseType?)
    suspend fun insertManyExpenses(vararg expense: Expense)
    suspend fun deleteOneExpense(expenseId: Long)
    suspend fun deleteExpensesByType(type: ExpenseType)
}

class ExpensesLocalDataSourceImpl @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExpensesLocalDataSource {
    override suspend fun getAllExpenses(): Flow<List<Expense>> {
        return withContext(ioDispatcher) {
            expenseDao.getAllExpenses()
        }
    }

    override suspend fun getOneExpense(expenseId: Long): Expense {
        return withContext(ioDispatcher) {
            expenseDao.getOneExpense(expenseId)
        }
    }

    override suspend fun updateOneExpense(
        id: Long,
        name: String?,
        cost: Float?,
        type: ExpenseType?
    ) {
        withContext(ioDispatcher) {
            expenseDao.updateOneExpense(id, name, cost, type)
        }
    }

    override suspend fun insertManyExpenses(vararg expense: Expense) {
        withContext(ioDispatcher) {
            expenseDao.insertManyExpenses(*expense)
        }
    }

    override suspend fun deleteOneExpense(expenseId: Long) {
        withContext(ioDispatcher) {
            expenseDao.deleteOneExpense(expenseId)
        }
    }

    override suspend fun deleteExpensesByType(type: ExpenseType) {
        withContext(ioDispatcher) {
            expenseDao.deleteAllExpensesByType(type)
        }
    }
}