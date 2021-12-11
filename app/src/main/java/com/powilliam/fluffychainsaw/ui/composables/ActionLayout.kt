package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ActionLayout(
    modifier: Modifier = Modifier,
    dividerColor: Color = MaterialTheme.colorScheme.outline,
    onClick: () -> Unit = {},
    icon: @Composable () -> Unit = {},
    trailing: @Composable () -> Unit = {},
    action: @Composable () -> Unit = {},
) {
    Column(modifier.fillMaxWidth()) {
        Surface {
            Row(
                modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier.size(24.dp)) {
                        icon()
                    }
                    Spacer(modifier.width(16.dp))
                    ProvideTextStyle(MaterialTheme.typography.bodyLarge) {
                        action()
                    }
                }

                ProvideTextStyle(MaterialTheme.typography.labelSmall) {
                    trailing()
                }
            }
        }
        Divider(color = dividerColor.copy(alpha = 0.1F))
    }
}