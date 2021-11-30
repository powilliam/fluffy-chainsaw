package com.powilliam.fluffychainsaw.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM Expense WHERE expenseId = :id")
    suspend fun getOneExpense(id: Long): Expense

    @Query("UPDATE Expense SET name = :name, cost = :cost, type = :type WHERE expenseId = :id")
    suspend fun updateOneExpense(id: Long, name: String?, cost: Float?, type: ExpenseType?)

    @Insert
    suspend fun insertManyExpenses(vararg expense: Expense)

    @Query("DELETE FROM Expense WHERE expenseId = :id")
    suspend fun deleteOneExpense(id: Long)
}