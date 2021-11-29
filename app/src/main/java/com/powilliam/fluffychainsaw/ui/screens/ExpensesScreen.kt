package com.powilliam.fluffychainsaw.ui.screens

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.ui.composables.ExpensesList
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

val expenses = listOf(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen(
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
        ExpensesList(expenses) { expense ->
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpensesScreenPreview() {
    FluffyChainsawTheme {
        ExpensesScreen()
    }
}