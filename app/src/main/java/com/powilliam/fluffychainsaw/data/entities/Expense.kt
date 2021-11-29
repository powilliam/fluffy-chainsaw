package com.powilliam.fluffychainsaw.data.entities

enum class ExpenseType {
    Fixed,
    Variable
}

data class Expense(val name: String, val cost: Float, val type: ExpenseType)
