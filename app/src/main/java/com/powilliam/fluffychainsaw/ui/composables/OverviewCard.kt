package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.ui.utils.currency

@Composable
fun OverviewCard(
    modifier: Modifier = Modifier,
    totalCost: Float,
    onAddMonthEnding: () -> Unit = {}
) {
    Surface(
        modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(text = currency(totalCost), style = MaterialTheme.typography.displaySmall)
            Text(text = "23 days left", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier.height(16.dp))
            Chip(
                color = MaterialTheme.colorScheme.primaryContainer,
                leading = {
                    Icon(
                        imageVector = Icons.Rounded.Schedule,
                        contentDescription = null
                    )
                },
                onClick = onAddMonthEnding
            ) {
                Text(text = "Add month ending")
            }
        }
    }
}

@Preview
@Composable
private fun OverviewCardPreview() {
    FluffyChainsawTheme {
        OverviewCard(totalCost = 89.97F)
    }
}