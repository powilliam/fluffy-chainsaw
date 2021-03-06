package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.data.entities.stringResourceId
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.viewmodels.ExpensesUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesList(
    modifier: Modifier = Modifier,
    expenses: List<Expense> = emptyList(),
    stickyHeader: (@Composable () -> Unit)? = null,
    overview: (@Composable () -> Unit)? = null,
    onNavigateToManageExpense: (Expense) -> Unit = {}
) {
    LazyColumn(modifier) {
        stickyHeader?.let { composable ->
            stickyHeader {
                ExpenseListStickyHeader {
                    composable()
                }
            }
        }

        overview?.let { composable ->
            item {
                ExpenseListOverviewCard {
                    composable()
                }
            }
        }

        expenses
            .groupBy { expense -> expense.type }
            .onEach { expenseByType ->
                item(expenseByType.key) {
                    ExpenseListItemHeader {
                        Text(text = stringResource(expenseByType.key.stringResourceId()))
                    }
                }

                items(
                    expenseByType.value,
                    key = { expense -> "${expense.expenseId}-${expense.name}" }
                ) { expense ->
                    ExpenseCard(expense = expense) {
                        onNavigateToManageExpense(expense)
                    }
                }
            }
    }
}

@Composable
private fun ExpenseListStickyHeader(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        content()
    }
}

@Composable
private fun ExpenseListOverviewCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
private fun ExpenseListItemHeader(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(modifier.fillMaxWidth()) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProvideTextStyle(
                MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun ExpensesListPreview() {
    FluffyChainsawTheme {
        ExpensesList(
            expenses = listOf(
                Expense(
                    name = "Disney+",
                    cost = 20.99F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "HBO Max",
                    cost = 29.99F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "Therapy",
                    cost = 180F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "FarCry 5",
                    cost = 40F,
                    type = ExpenseType.Variable
                )
            ),
        )
    }
}

@Preview
@Composable
private fun ExpensesListWithOneTypeOfExpensePreview() {
    FluffyChainsawTheme {
        ExpensesList(
            expenses = listOf(
                Expense(
                    name = "Disney+",
                    cost = 20.99F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "HBO Max",
                    cost = 29.99F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "Therapy",
                    cost = 180F,
                    type = ExpenseType.Fixed
                ),
            )
        )
    }
}

@Preview
@Composable
private fun ExpensesListWithContainedTextFieldPreview() {
    FluffyChainsawTheme {
        ExpensesList(
            expenses = listOf(
                Expense(
                    name = "Disney+",
                    cost = 20.99F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "HBO Max",
                    cost = 29.99F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "Therapy",
                    cost = 180F,
                    type = ExpenseType.Fixed
                ),
                Expense(
                    name = "FarCry 5",
                    cost = 40F,
                    type = ExpenseType.Variable
                )
            ),
            stickyHeader = {
                ContainedTextField(
                    shape = RoundedCornerShape(percent = 50),
                    value = "",
                    placeholder = "Search",
                    onValueChange = {},
                    leading = {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                    }
                )
            }
        )
    }
}

@Preview
@Composable
private fun ExpensesListWithOverviewCardPreview() {
    val uiState = ExpensesUiState(
        expenses = listOf(
            Expense(
                name = "Disney+",
                cost = 20.99F,
                type = ExpenseType.Fixed
            ),
            Expense(
                name = "HBO Max",
                cost = 29.99F,
                type = ExpenseType.Fixed
            ),
            Expense(
                name = "Therapy",
                cost = 180F,
                type = ExpenseType.Fixed
            ),
            Expense(
                name = "FarCry 5",
                cost = 40F,
                type = ExpenseType.Variable
            )
        )
    )

    FluffyChainsawTheme {
        ExpensesList(
            expenses = uiState.expenses,
            overview = {
                OverviewCard(uiState = uiState)
            }
        )
    }
}