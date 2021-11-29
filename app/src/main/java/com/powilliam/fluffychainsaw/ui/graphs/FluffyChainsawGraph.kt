package com.powilliam.fluffychainsaw.ui.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.powilliam.fluffychainsaw.ui.constants.Screen
import com.powilliam.fluffychainsaw.ui.screens.ExpensesScreen

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun FluffyChainsawGraph(
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator)
) {
    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(navController, startDestination = Screen.Expenses.route) {
            screens()
        }
    }
}

private fun NavGraphBuilder.screens() {
    composable(Screen.Expenses.route, Screen.Expenses.arguments) {
        ExpensesScreen()
    }
}