package com.powilliam.fluffychainsaw.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.ui.composables.ExpensesList
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.viewmodels.ExpensesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    uiState: ExpensesUiState = ExpensesUiState(),
    onNavigateToManageExpense: (Expense?) -> Unit = {}
) {
    Scaffold(
        topBar = { ExpensesScreenAppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToManageExpense(null) }
            ) {
                Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = null)
            }
        }
    ) {
        ExpensesList(uiState.expenses) { expense ->
            onNavigateToManageExpense(expense)
        }
    }
}

@Composable
private fun ExpensesScreenAppBar() {
    LargeTopAppBar(
        title = {
            Text(text = stringResource(R.string.expenses_screen_title))
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPreview() {
    FluffyChainsawTheme {
        ExpensesScreen()
    }
}