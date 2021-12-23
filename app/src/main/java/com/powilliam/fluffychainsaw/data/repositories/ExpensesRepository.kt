package com.powilliam.fluffychainsaw.data.repositories

import com.powilliam.fluffychainsaw.data.datasources.ExpensesLocalDataSource
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ExpensesRepository {
    suspend fun getAll(): Flow<List<Expense>>
    suspend fun getOne(expenseId: Long): Expense
    suspend fun updateOne(id: Long, name: String?, cost: Float?, type: ExpenseType?)
    suspend fun insertMany(vararg expense: Expense)
    suspend fun deleteOne(expenseId: Long)
    suspend fun deleteAllVariableExpenses()
}

class ExpensesRepositoryImpl @Inject constructor(
    private val expensesLocalDataSource: ExpensesLocalDataSource
) : ExpensesRepository {
    override suspend fun getAll(): Flow<List<Expense>> =
        expensesLocalDataSource.getAll()

    override suspend fun getOne(expenseId: Long): Expense =
        expensesLocalDataSource.getOne(expenseId)

    override suspend fun updateOne(
        id: Long,
        name: String?,
        cost: Float?,
        type: ExpenseType?
    ) = expensesLocalDataSource.updateOne(id, name, cost, type)

    override suspend fun insertMany(vararg expense: Expense) =
        expensesLocalDataSource.insertMany(*expense)

    override suspend fun deleteOne(expenseId: Long) =
        expensesLocalDataSource.deleteOne(expenseId)

    override suspend fun deleteAllVariableExpenses() =
        expensesLocalDataSource.deleteAllByType(ExpenseType.Variable)
}