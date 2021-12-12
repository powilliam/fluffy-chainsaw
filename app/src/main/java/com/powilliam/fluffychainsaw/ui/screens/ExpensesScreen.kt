package com.powilliam.fluffychainsaw.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.ui.composables.ContainedTextField
import com.powilliam.fluffychainsaw.ui.composables.ExpensesList
import com.powilliam.fluffychainsaw.ui.composables.OverviewCard
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.viewmodels.ExpensesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
    modifier: Modifier = Modifier,
    uiState: ExpensesUiState = ExpensesUiState(),
    onSearch: (String) -> Unit = {},
    onNavigateToManageExpense: (Expense?) -> Unit = {}
) {
    val filteredExpenses = with(uiState) {
        expenses.filter { expense ->
            expense.name.contains(
                query,
                ignoreCase = false
            ) or expense.name.contentEquals(query, ignoreCase = false)
        }
    }
    val totalCost = with(uiState) {
        expenses.fold(0F) { totalCost, expense -> totalCost + expense.cost }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigateToManageExpense(null) }
            ) {
                Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = null)
            }
        }
    ) {
        ExpensesList(
            modifier.fillMaxSize(),
            expenses = filteredExpenses,
            stickyHeader = {
                ContainedTextField(
                    value = uiState.query,
                    placeholder = stringResource(R.string.expenses_search_placeholder),
                    shape = RoundedCornerShape(percent = 50),
                    onValueChange = onSearch,
                    leading = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null
                        )
                    },
                )
            },
            overview = {
                OverviewCard(totalCost = totalCost)
            }
        ) { expense ->
            onNavigateToManageExpense(expense)
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