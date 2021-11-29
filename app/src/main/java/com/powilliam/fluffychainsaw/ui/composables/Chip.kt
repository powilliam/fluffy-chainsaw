package com.powilliam.fluffychainsaw.ui.composables

import android.content.res.Configuration
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
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primaryContainer,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {}
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(percent = 50)
    ) {
        Row(
            modifier
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp, horizontal = 12.dp)
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
private fun ChipPreview() {
    FluffyChainsawTheme {
        Chip {
            Text(text = "Hello World")
        }
    }
}