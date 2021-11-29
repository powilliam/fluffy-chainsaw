package com.powilliam.fluffychainsaw.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ExpenseType {
    Fixed,
    Variable
}

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val expenseId: Long = 0,
    val name: String,
    val cost: Float,
    val type: ExpenseType
)
