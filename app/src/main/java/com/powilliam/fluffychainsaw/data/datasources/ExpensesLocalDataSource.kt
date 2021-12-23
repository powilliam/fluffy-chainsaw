package com.powilliam.fluffychainsaw.data.datasources

import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseCategory
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ExpensesLocalDataSource {
    suspend fun getAll(): Flow<List<Expense>>
    suspend fun getOne(expenseId: Long): Expense
    suspend fun updateOne(
        id: Long,
        name: String?,
        cost: Float?,
        type: ExpenseType?,
        category: ExpenseCategory?
    )

    suspend fun insertMany(vararg expense: Expense)
    suspend fun deleteOne(expenseId: Long)
    suspend fun deleteAllByType(type: ExpenseType)
}

class ExpensesLocalDataSourceImpl @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ExpensesLocalDataSource {
    override suspend fun getAll(): Flow<List<Expense>> {
        return withContext(ioDispatcher) {
            expenseDao.getAll()
        }
    }

    override suspend fun getOne(expenseId: Long): Expense {
        return withContext(ioDispatcher) {
            expenseDao.getOne(expenseId)
        }
    }

    override suspend fun updateOne(
        id: Long,
        name: String?,
        cost: Float?,
        type: ExpenseType?,
        category: ExpenseCategory?
    ) {
        withContext(ioDispatcher) {
            expenseDao.updateOne(id, name, cost, type, category)
        }
    }

    override suspend fun insertMany(vararg expense: Expense) {
        withContext(ioDispatcher) {
            expenseDao.insertMany(*expense)
        }
    }

    override suspend fun deleteOne(expenseId: Long) {
        withContext(ioDispatcher) {
            expenseDao.deleteOne(expenseId)
        }
    }

    override suspend fun deleteAllByType(type: ExpenseType) {
        withContext(ioDispatcher) {
            expenseDao.deleteAllByType(type)
        }
    }
}