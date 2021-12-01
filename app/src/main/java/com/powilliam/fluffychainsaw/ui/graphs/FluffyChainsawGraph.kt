package com.powilliam.fluffychainsaw.ui.graphs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.navigation.material.*
import com.powilliam.fluffychainsaw.ui.bottomsheets.ManageExpenseBottomSheet
import com.powilliam.fluffychainsaw.ui.constants.Screen
import com.powilliam.fluffychainsaw.ui.screens.ExpensesScreen
import com.powilliam.fluffychainsaw.ui.viewmodels.ExpensesViewModel
import com.powilliam.fluffychainsaw.ui.viewmodels.ManageExpenseViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun FluffyChainsawGraph(
    modifier: Modifier = Modifier,
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator)
) {
    ModalBottomSheetLayout(
        bottomSheetNavigator,
        modifier.navigationBarsWithImePadding(),
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetElevation = 12.dp,
        scrimColor = Color.Transparent
    ) {
        NavHost(navController, startDestination = Screen.Expenses.route) {
            screens(navController)
            bottomSheets(navController)
        }
    }
}

private fun NavGraphBuilder.screens(
    navController: NavHostController
) {
    composable(Screen.Expenses.route, Screen.Expenses.arguments) {
        val viewModel = hiltViewModel<ExpensesViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ExpensesScreen(
            uiState,
            onSearch = viewModel::onSearch
        ) { expense ->
            navController.navigate(Screen.ManageExpense.navigate(expense))
        }
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.bottomSheets(navController: NavHostController) {
    bottomSheet(Screen.ManageExpense.route, Screen.ManageExpense.arguments) { navBackStackEntry ->
        val viewMode = navBackStackEntry.arguments?.getString("viewMode")
        val expenseId = navBackStackEntry.arguments?.getLong("expenseId")

        val viewModel = hiltViewModel<ManageExpenseViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(viewMode, expenseId) {
            launch {
                viewModel.onSetUiStateBasedOnViewMode(viewMode, expenseId)
            }
        }

        ManageExpenseBottomSheet(
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