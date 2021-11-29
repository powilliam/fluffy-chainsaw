package com.powilliam.fluffychainsaw.ui.constants

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.powilliam.fluffychainsaw.data.entities.Expense

sealed class ManageExpenseViewMode(val alias: String) {
    object ViewingOne : ManageExpenseViewMode("read")
    object InsertingOne : ManageExpenseViewMode("write")
}

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object Expenses : Screen("/expenses")
    object ManageExpense : Screen(
        "/manage-expense?viewMode={viewMode}&expenseId={expenseId}",
        listOf(
            navArgument("viewMode") {
                defaultValue = ManageExpenseViewMode.InsertingOne.alias
                type = NavType.StringType
            },
            navArgument("expenseId") {
                defaultValue = 0
                type = NavType.LongType
            }
        )
    ) {
        fun navigate(expense: Expense?): String {
            val viewMode = if (expense == null) {
                ManageExpenseViewMode.InsertingOne
            } else {
                ManageExpenseViewMode.ViewingOne
            }

            return ManageExpense.route
                .replace("{viewMode}", viewMode.alias)
                .replace("{expenseId}", "${expense?.expenseId ?: 0}")
        }
    }
}
