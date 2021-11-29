package com.powilliam.fluffychainsaw.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.powilliam.fluffychainsaw.data.entities.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getAllExpenses(): Flow<List<Expense>>

    @Insert
    suspend fun insertManyExpenses(vararg expense: Expense)
}