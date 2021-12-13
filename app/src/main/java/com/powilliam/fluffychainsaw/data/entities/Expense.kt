package com.powilliam.fluffychainsaw.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.powilliam.fluffychainsaw.R

enum class ExpenseType {
    Fixed,
    Variable
}

fun ExpenseType.stringResourceId(): Int {
    return when (this) {
        ExpenseType.Variable -> R.string.expense_type_variable
        ExpenseType.Fixed -> R.string.expense_type_fixed
    }
}

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val expenseId: Long = 0,
    val name: String,
    val cost: Float,
    val type: ExpenseType
)
