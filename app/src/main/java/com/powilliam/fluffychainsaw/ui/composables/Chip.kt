package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    tonalElevation: Dp = 0.dp,
    onClick: () -> Unit = {},
    leading: (@Composable () -> Unit)? = null,
    content: @Composable RowScope.() -> Unit = {}
) {
    val endPadding = if (leading != null) 12.dp else 4.dp

    Surface(
        color = color,
        shape = RoundedCornerShape(8.dp),
        tonalElevation = tonalElevation
    ) {
        Row(
            modifier
                .clickable(onClick = onClick)
                .padding(start = 4.dp, end = endPadding, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leading?.let { composable ->
                Box(modifier.size(24.dp)) {
                    composable()
                }
                Spacer(modifier.width(4.dp))
            }

            ProvideTextStyle(MaterialTheme.typography.labelLarge) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun ChipPreview() {
    FluffyChainsawTheme {
        Chip {
            Text(text = "Hello World")
        }
    }
}

@Preview
@Composable
private fun ChipPreviewWithTonalElevation() {
    FluffyChainsawTheme {
        Chip(tonalElevation = 2.dp) {
            Text(text = "Hello World")
        }
    }
}