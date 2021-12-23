package com.powilliam.fluffychainsaw.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.powilliam.fluffychainsaw.R

enum class ExpenseType {
    Fixed,
    Variable
}

enum class ExpenseCategory {
    Entertainment,
    Health,
    Food,
    Study
}

fun ExpenseType.stringResourceId(): Int {
    return when (this) {
        ExpenseType.Variable -> R.string.expense_type_variable
        ExpenseType.Fixed -> R.string.expense_type_fixed
    }
}

fun ExpenseCategory?.stringResourceId(): Int {
    return when (this) {
        ExpenseCategory.Entertainment -> R.string.expense_category_entertainment
        ExpenseCategory.Health -> R.string.expense_category_health
        ExpenseCategory.Food -> R.string.expense_category_food
        ExpenseCategory.Study -> R.string.expense_category_study
        else -> R.string.expense_category_no_category
    }
}

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val expenseId: Long = 0,
    val name: String,
    val cost: Float,
    val type: ExpenseType,
    val category: ExpenseCategory? = null
)
