package com.powilliam.fluffychainsaw.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.entities.Expense

@Database(entities = [Expense::class], version = 1)
abstract class FluffyChainsawDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}