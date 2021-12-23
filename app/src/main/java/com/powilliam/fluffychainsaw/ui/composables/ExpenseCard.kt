package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseCategory
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.utils.currency

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    expense: Expense,
    onClick: () -> Unit = {}
) {
    Surface(modifier.fillMaxWidth()) {
        Row(
            modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable(onClick = onClick)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    expense.category.Icon(
                        modifier = modifier.align(Alignment.Center),
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
                Spacer(modifier.width(16.dp))
                Column {
                    Text(text = expense.name, style = MaterialTheme.typography.bodyLarge)
                    Text(text = currency(expense.cost), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun ExpenseCategory?.Icon(
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onTertiaryContainer
) {
    val imageVector = when (this) {
        ExpenseCategory.Entertainment -> Icons.Rounded.SportsEsports
        ExpenseCategory.Health -> Icons.Rounded.HealthAndSafety
        ExpenseCategory.Food -> Icons.Rounded.Restaurant
        ExpenseCategory.Study -> Icons.Rounded.School
        else -> Icons.Rounded.Paid
    }

    Icon(modifier = modifier, imageVector = imageVector, contentDescription = null, tint = tint)
}

@Preview
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
