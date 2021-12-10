package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    content: @Composable RowScope.() -> Unit = {}
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(8.dp),
        tonalElevation = tonalElevation
    ) {
        Row(
            modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
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