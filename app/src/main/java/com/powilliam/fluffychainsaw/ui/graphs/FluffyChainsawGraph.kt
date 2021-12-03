package com.powilliam.fluffychainsaw.ui.graphs

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.powilliam.fluffychainsaw.ui.constants.Screen
import com.powilliam.fluffychainsaw.ui.screens.ExpensesScreen
import com.powilliam.fluffychainsaw.ui.screens.ManageExpenseScreen
import com.powilliam.fluffychainsaw.ui.viewmodels.ExpensesViewModel
import com.powilliam.fluffychainsaw.ui.viewmodels.ManageExpenseViewModel
import kotlinx.coroutines.launch

@Composable
fun FluffyChainsawGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = Screen.Expenses.route) {
        screens(navController)
    }
}

private fun NavGraphBuilder.screens(navController: NavHostController) {
    composable(Screen.Expenses.route, Screen.Expenses.arguments) {
        val viewModel = hiltViewModel<ExpensesViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ExpensesScreen(
            uiState = uiState,
            onSearch = viewModel::onSearch
        ) { expense ->
            navController.navigate(Screen.ManageExpense.navigate(expense)) {
                popUpTo(Screen.ManageExpense.route)
            }
        }
    }

    composable(Screen.ManageExpense.route, Screen.ManageExpense.arguments) { navBackStackEntry ->
        val viewMode = navBackStackEntry.arguments?.getString("viewMode")
        val expenseId = navBackStackEntry.arguments?.getLong("expenseId")

        val viewModel = hiltViewModel<ManageExpenseViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            launch { viewModel.onSetUiStateBasedOnViewMode(viewMode, expenseId) }
        }

        ManageExpenseScreen(
            uiState = uiState,
            onChangeName = viewModel::onChangeName,
            onChangeCost = viewModel::onChangeCost,
            onChangeType = viewModel::onChangeType,
            onCancel = navController::popBackStack,
            onDelete = {
                viewModel.onDelete()
                navController.popBackStack()
            }
        ) {
            viewModel.onInsertOrUpdate()
            navController.popBackStack()
        }
    }
}