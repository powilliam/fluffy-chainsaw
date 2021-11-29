package com.powilliam.fluffychainsaw.ui.graphs

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        ExpensesScreen {
            navController.navigate(Screen.ManageExpense.route)
        }
    }
}

@OptIn(ExperimentalMaterialNavigationApi::class)
private fun NavGraphBuilder.bottomSheets(navController: NavHostController) {
    bottomSheet(Screen.ManageExpense.route, Screen.Expenses.arguments) {
        ManageExpenseBottomSheet(onCancel = navController::popBackStack) {
            navController.popBackStack()
        }
    }
}