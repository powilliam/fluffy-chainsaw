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
    fun getAll(): Flow<List<Expense>>

    @Query("SELECT * FROM Expense WHERE expenseId = :id")
    suspend fun getOne(id: Long): Expense

    @Query("UPDATE Expense SET name = :name, cost = :cost, type = :type WHERE expenseId = :id")
    suspend fun updateOne(id: Long, name: String?, cost: Float?, type: ExpenseType?)

    @Insert
    suspend fun insertMany(vararg expense: Expense)

    @Query("DELETE FROM Expense WHERE expenseId = :id")
    suspend fun deleteOne(id: Long)

    @Query("DELETE FROM Expense WHERE type = :type")
    suspend fun deleteAllByType(type: ExpenseType = ExpenseType.Variable)
}