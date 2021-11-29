package com.powilliam.fluffychainsaw.ui.constants

import androidx.navigation.NamedNavArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object Expenses : Screen("/expenses")
    object ManageExpense : Screen("/manage-expense")
}
