package com.powilliam.fluffychainsaw.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import java.text.NumberFormat
import com.powilliam.fluffychainsaw.R

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    expense: Expense,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier
                .clickable(onClick = onClick)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val chipColor = when (expense.type) {
                ExpenseType.Variable -> MaterialTheme.colorScheme.tertiaryContainer
                else -> MaterialTheme.colorScheme.secondaryContainer
            }

            Column(modifier.fillMaxWidth(fraction = 0.65F)) {
                val cost = try {
                    NumberFormat
                        .getCurrencyInstance()
                        .also { formatter ->
                            formatter.maximumFractionDigits = 2
                        }
                        .format(expense.cost)
                } catch (exception: NumberFormatException) {
                    expense.cost
                }

                Text(text = expense.name, style = MaterialTheme.typography.headlineSmall)
                Text(text = "$cost", style = MaterialTheme.typography.bodyLarge)
            }
            Chip(color = chipColor) {
                val id = when (expense.type) {
                    ExpenseType.Fixed -> R.string.expense_type_fixed
                    else -> R.string.expense_type_variable
                }

                Text(text = stringResource(id))
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpenseCardPreview() {
    FluffyChainsawTheme {
        Column {
            ExpenseCard(
                expense = Expense(
                    name = "Disney+",
                    cost = 29.99F,
                    type = ExpenseType.Fixed
                )
            )
            ExpenseCard(
                expense = Expense(
                    name = "HBO Max",
                    cost = 29F,
                    type = ExpenseType.Variable
                )
            )
        }
    }
}