package com.powilliam.fluffychainsaw.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpensesList(
    expenses: List<Expense> = emptyList(),
    onNavigateToManageExpense: (Expense) -> Unit = {}
) {
    val expensesByType by rememberUpdatedState(expenses.groupBy { expense -> expense.type })
    val hasMoreThanOneTypeOfExpense by remember {
        derivedStateOf {
            expensesByType.keys.size > 1
        }
    }

    LazyColumn {
        expensesByType.forEach { expenseByType ->
            if (hasMoreThanOneTypeOfExpense) {
                stickyHeader(expenseByType.key) {
                    ExpenseListStickyHeader {
                        val id = when (expenseByType.key) {
                            ExpenseType.Fixed -> R.string.expense_type_fixed
                            else -> R.string.expense_type_variable
                        }

                        Text(text = stringResource(id))
                    }
                }
            }

            // TODO: Key should return an id
            items(expenseByType.value, key = { expense -> expense.name }) { expense ->
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
    content: @Composable RowScope.() -> Unit
) {
    Surface(modifier.fillMaxWidth()) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        ) {
            ProvideTextStyle(MaterialTheme.typography.labelLarge) {
                content()
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpensesListPreview() {
    FluffyChainsawTheme {
        ExpensesList(
            listOf(
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
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpensesListWithOneTypeOfExpensePreview() {
    FluffyChainsawTheme {
        ExpensesList(
            listOf(
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