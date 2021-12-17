package com.powilliam.fluffychainsaw.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.ui.composables.*
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.viewmodels.ExpensesUiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    uiState: ExpensesUiState = ExpensesUiState(),
    onSearch: (String) -> Unit = {},
    onSelectMonthEnding: (Long) -> Unit = {},
    onNavigateToManageExpense: (Expense?) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Column {
                SearchTextField(value = uiState.query, onValueChange = onSearch)
                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2F))
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
                onClick = { onNavigateToManageExpense(null) }
            ) {
                Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = null)
            }
        }
    ) {
        LoadingTransitionScene(isLoading = uiState.isLoading) {
            ExpensesList(
                modifier.fillMaxSize(),
                expenses = uiState.filteredExpenses,
                overview = {
                    OverviewCard(
                        uiState = uiState,
                        onSelectMonthEnding = onSelectMonthEnding
                    )
                }
            ) { expense ->
                onNavigateToManageExpense(expense)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpensesScreenPreview() {
    FluffyChainsawTheme {
        ExpensesScreen()
    }
}