package com.powilliam.fluffychainsaw.data.repositories

import com.powilliam.fluffychainsaw.data.datasources.ExpensesLocalDataSource
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ExpensesRepository {
    suspend fun getAllExpenses(): Flow<List<Expense>>
    suspend fun getOneExpense(expenseId: Long): Expense
    suspend fun updateOneExpense(id: Long, name: String?, cost: Float?, type: ExpenseType?)
    suspend fun insertManyExpenses(vararg expense: Expense)
    suspend fun deleteOneExpense(expenseId: Long)
    suspend fun deleteAllVariableExpenses()
}

class ExpensesRepositoryImpl @Inject constructor(
    private val expensesLocalDataSource: ExpensesLocalDataSource
) : ExpensesRepository {
    override suspend fun getAllExpenses(): Flow<List<Expense>> =
        expensesLocalDataSource.getAllExpenses()

    override suspend fun getOneExpense(expenseId: Long): Expense =
        expensesLocalDataSource.getOneExpense(expenseId)

    override suspend fun updateOneExpense(
        id: Long,
        name: String?,
        cost: Float?,
        type: ExpenseType?
    ) = expensesLocalDataSource.updateOneExpense(id, name, cost, type)

    override suspend fun insertManyExpenses(vararg expense: Expense) =
        expensesLocalDataSource.insertManyExpenses(*expense)

    override suspend fun deleteOneExpense(expenseId: Long) =
        expensesLocalDataSource.deleteOneExpense(expenseId)

    override suspend fun deleteAllVariableExpenses() =
        expensesLocalDataSource.deleteExpensesByType(ExpenseType.Variable)
}